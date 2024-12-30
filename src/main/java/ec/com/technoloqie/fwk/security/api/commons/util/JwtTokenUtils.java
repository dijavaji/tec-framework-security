package ec.com.technoloqie.fwk.security.api.commons.util;


import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import ec.com.technoloqie.fwk.security.api.commons.AuthWSConstants;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtTokenUtils {
	
	private static final Logger logger = LoggerFactory.getLogger(JwtTokenUtils.class);

    @Value("${ec.com.technoloqie.fwk.security.jwt.acces.token.secret}")
    //private static String jwtSecret;
    private static String ACCES_TOKEN_SECRET = "6ccd4ed8b9b02c0cf0276f0f06c24c6c90739da87fc5cd06c12d5f563c577a2f";

    @Value("${ec.com.technoloqie.fwk.security.jwt.expiration-milliseconds}")
    private static long jwtExpirationDate = 2592000;
    //private final static long ACCES_TOKEN_VALIDITY_SECONDS= 1800;//30min  //2592000 30dias*24horasx3600s
    
    // generate JWT token
    /*public String generateToken(Authentication authentication){
    	 String username = authentication.getName();

         Date currentDate = new Date();

         Date expireDate = new Date(currentDate.getTime() + jwtExpirationDate);

        String token = Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(expireDate)
                .signWith(key())
                .compact();
        return token;
    }*/
    
    public static String createToken(String nombre, String email, Integer roles) {
		long expirationTime = jwtExpirationDate * 1000;
		//Date expirationDate = new Date(System.currentTimeMillis() + expirationTime);
    	Date expirationDate = new Date(System.currentTimeMillis() + expirationTime);
		Map< String, Object> extra = new HashMap<>();
		extra.put("username", nombre);
		extra.put("email", email);
		extra.put("roles", roles);
		
		return Jwts.builder()
				.setSubject(email)
				.setIssuedAt(new Date())
				.setIssuer(AuthWSConstants.JWT_ISSUER)
				.setAudience(AuthWSConstants.JWT_AUDIENCE)
				.setExpiration(expirationDate) //.addClaims(extra).signWith(Keys.hmacShaKeyFor(jwtSecret.getBytes())).compact();
				.addClaims(extra).signWith(key()).compact();
		
	}
    
    public String signRefreshToken(String userId) {
    	//agrego vencimiento de 1h
    	long expirationTime = (jwtExpirationDate + 3600) * 1000;
		//Date expirationDate = new Date(System.currentTimeMillis() + expirationTime);
    	Date expirationDate = new Date(System.currentTimeMillis() + expirationTime);
		Map< String, Object> extra = new HashMap<>();
		//extra.put("nombre", nombre);
		//extra.put("email", email);
		//extra.put("userType", 1);
		
		return Jwts.builder()
				.setSubject(userId)
				.setIssuedAt(new Date())
				.setIssuer(AuthWSConstants.JWT_ISSUER)
				.setAudience(AuthWSConstants.JWT_AUDIENCE)
				.setExpiration(expirationDate) //.addClaims(extra).signWith(Keys.hmacShaKeyFor(jwtSecret.getBytes())).compact();
				.addClaims(extra).signWith(key()).compact();
    }

    private static Key key(){
        return Keys.hmacShaKeyFor(
                Decoders.BASE64.decode(ACCES_TOKEN_SECRET)
        );
    }

    // get username from Jwt token
    public  String getUsername(String token){
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(key())
                .build()
                .parseClaimsJws(token)
                .getBody();
        String username = claims.getSubject();
        return username;
    }

    // validate Jwt token
    public boolean validateToken(String token){
        try{
            Jwts.parserBuilder()
                    .setSigningKey(key())
                    .build()
                    .parse(token);
            return true;
        } catch (MalformedJwtException e) {
            logger.error("Invalid JWT token: {}", e.getMessage());
        } catch (ExpiredJwtException e) {
            logger.error("JWT token is expired: {}", e.getMessage());
        } catch (UnsupportedJwtException e) {
            logger.error("JWT token is unsupported: {}", e.getMessage());
        } catch (IllegalArgumentException e) {
            logger.error("JWT claims string is empty: {}", e.getMessage());
        }
        return false;
    }
    
    @SuppressWarnings("static-access")
	public UsernamePasswordAuthenticationToken getAuthentication(UserDetails userDetails) {
		try {
			//Claims claims = Jwts.parserBuilder().setSigningKey(key()).build().parseClaimsJws(token).getBody();
			
			//String email = claims.getSubject();
			//logger.info("************** {}",userDetails.getUsername());
			return new UsernamePasswordAuthenticationToken(userDetails, userDetails.getPassword(), userDetails.getAuthorities());
		}catch(JwtException e) {
			logger.error("Error al realizar autenticacion token incorrecto invalido o expirado.",e);
			return null;
		}
	}
}
