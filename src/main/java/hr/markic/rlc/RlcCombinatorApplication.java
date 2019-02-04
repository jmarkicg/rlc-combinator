package hr.markic.rlc;

import hr.markic.rlc.config.PropertyConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class RlcCombinatorApplication {

	private static final Logger log = LoggerFactory.getLogger(PropertyConfig.class);

	public static void main(String[] args) {
		SpringApplication.run(RlcCombinatorApplication.class, args);
		log.info("------------------Application started.----------------------");
	}

}