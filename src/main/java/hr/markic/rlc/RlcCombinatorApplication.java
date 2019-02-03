package hr.markic.rlc;

import hr.markic.rlc.repository.CapacitorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class RlcCombinatorApplication {

	CapacitorRepository capacitorService;

	@Autowired
	public RlcCombinatorApplication(CapacitorRepository service){
		this.capacitorService = service;
	}
	public static void main(String[] args) {
		SpringApplication.run(RlcCombinatorApplication.class, args);
		System.out.println("test");
	}

}

