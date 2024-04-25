package ec.com.technoloqie.api.customer;

import java.time.Clock;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;

import org.junit.jupiter.api.Test;

//@SpringBootTest
class TecCustomerServiceApplicationTests {

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

}
