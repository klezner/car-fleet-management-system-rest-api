package pl.kl.companycarfleetmanagementsystem;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import pl.kl.companycarfleetmanagementsystem.user.ApplicationUser;
import pl.kl.companycarfleetmanagementsystem.user.ApplicationUserRepository;

import java.util.Collections;

@Component
@Profile("DEV")
@RequiredArgsConstructor
public class ApplicationRunner implements CommandLineRunner {

    private final ApplicationUserRepository applicationUserRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) {
        applicationUserRepository.deleteAll();
        applicationUserRepository.save(new ApplicationUser("admin", passwordEncoder.encode("admin1"), Collections.singletonList(() -> "ROLE_ADMIN;ROLE_USER")));
        applicationUserRepository.save(new ApplicationUser("user", passwordEncoder.encode("user1"), Collections.singletonList(() -> "ROLE_USER")));
    }
}
