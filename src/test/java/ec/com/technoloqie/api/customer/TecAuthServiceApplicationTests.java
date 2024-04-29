package ec.com.technoloqie.api.customer;

import static org.junit.jupiter.api.Assertions.fail;

import java.time.Clock;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import ec.com.technoloqie.api.customer.dto.PersonDto;
import ec.com.technoloqie.api.customer.dto.SignUpDto;
import ec.com.technoloqie.api.customer.service.IUserService;
import jakarta.security.auth.message.AuthException;
import lombok.extern.slf4j.Slf4j;

/**
 * Utilizada para realizar test unitarios sobre los servicios de autorizacion.
 * @author dvasquez
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
class TecAuthServiceApplicationTests {
	
	@Autowired
	private IUserService userService;
	
	@Before
	public void setUp() throws AuthException{
		
	}

	@Test
	void contextLoads() {
        LocalDate localDate1 = LocalDate.now();

        System.out.println("LocalDate1: " + localDate1);
        LocalDate localDate2 = LocalDate.now(Clock.systemUTC());

        System.out.println("LocalDate2: " + localDate2);

        

        LocalDate localDate3 = LocalDate.now(ZoneId.systemDefault());

        System.out.println("LocalDate3: " + localDate3);
        
        System.out.println("LocalDate4: " + new Date());
        
        System.out.println("LocalDate4: " + LocalTime.now());
        
        LocalDateTime dateTime = LocalDateTime.now();;
        System.out.println(dateTime);
  
        // add days
        dateTime = dateTime.plusDays(10L);
        System.out.println(dateTime);
  
       // add hours
       dateTime = dateTime.plusHours(10L);
       System.out.println(dateTime);
  
       // add minutes
       dateTime.plusMinutes(10L);
       System.out.println(dateTime);
  
       ZonedDateTime zonedDateTime = ZonedDateTime.of(dateTime, ZoneId.systemDefault());
       long date = zonedDateTime.toInstant().toEpochMilli();

       System.out.println(date);
	}
	
	@Test
	public void testServiceSaveUser() {
		log.info("testServiceSaveUser.");
		SignUpDto signUpDto = new SignUpDto();
		signUpDto.setUsername("admin");
		signUpDto.setEmail("admin@pbx.com");
		signUpDto.setPassword("123456");
		signUpDto.setCreatedBy("test");
		PersonDto personDto = new PersonDto();
		personDto.setFirstName("Pepito");
		personDto.setLastName("Harvard");
		personDto.setGender("M");
		personDto.setBirthDate(new Date(2000, 01, 01));
		personDto.setAddress("La villaflora");
		personDto.setCreatedBy("test");
		signUpDto.setPerson(personDto);
		try{
			this.userService.createUser(signUpDto);
		}catch(Exception e) {
			log.error("testServiceSaveUser.");
			fail("Error in testServiceSaveUser.");
		}
	}

}
