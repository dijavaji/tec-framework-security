package ec.com.technoloqie.fwk.security.api;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

import lombok.extern.slf4j.Slf4j;

@SpringBootApplication
@Slf4j
public class TecFwkSecurityServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(TecFwkSecurityServiceApplication.class, args);
	}
	
	@EventListener(ApplicationReadyEvent.class)
	public void doAfterStartup() {
		log.info("The Security Application has started");
	}

}
