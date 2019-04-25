package hr.markic.rlc;

import hr.markic.rlc.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class DataInitializer implements CommandLineRunner {

    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
/*
        this.userRepository.save(User.builder()
            .username("hrvoje")
            .password(this.passwordEncoder.encode("123"))
            .roles(Arrays.asList( "ROLE_USER"))
            .build()
        );

        this.userRepository.save(User.builder()
            .username("jelena")
            .password(this.passwordEncoder.encode("12"))
            .roles(Arrays.asList("ROLE_USER", "ROLE_ADMIN"))
            .build()
        );

        log.debug("printing all users...");
        this.userRepository.findAll().forEach(v -> log.debug(" User :" + v.toString()));
        */

    }
}
