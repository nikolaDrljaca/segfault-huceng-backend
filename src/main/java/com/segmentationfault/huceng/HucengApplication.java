package com.segmentationfault.huceng;

import com.segmentationfault.huceng.entity.AppUser;
import com.segmentationfault.huceng.entity.Role;
import com.segmentationfault.huceng.startup.StartupService;
import com.segmentationfault.huceng.util.RoleUtil;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.ArrayList;

@SpringBootApplication
public class HucengApplication {

    public static void main(String[] args) {
        SpringApplication.run(HucengApplication.class, args);
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * Allow requests from the localhost:3000 to pass through to Spring Security
     * Cross-Origin mapping.
     *
     * @return
     */
    @Bean
    WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**").allowedOrigins("http://localhost:3000");
            }
        };
    }

    /**
     * This code will be executed before the app starts.
     * Ideal place to initialize starting users for testing.
     * Also, admins have to be added here since they are predetermined.
     *
     * @return
     */
    @Bean
    CommandLineRunner runner(StartupService service) {
        return args -> {
            service.saveRole(new Role(null, RoleUtil.ROLE_ADMIN));
            service.saveRole(new Role(null, RoleUtil.ROLE_ACADEMICIAN));
            service.saveRole(new Role(null, RoleUtil.ROLE_STUDENT));
            service.saveRole(new Role(null, RoleUtil.ROLE_GRADUATE));

            service.saveUser(new AppUser(null, "Nikola", "Drljaca", "drljacan@outlook.com", "nikolaDrljaca", "1234", new ArrayList<>()));
            service.saveUser(new AppUser(null, "Kayra ", "Uckilinc", "kayrauckilinc1@gmail.com", "kayrauckilinc", "1234", new ArrayList<>()));
            service.saveUser(new AppUser(null, "Davut", "Kulaksiz", "kulaksiz@gmail.com", "davutkulaksiz", "1234", new ArrayList<>()));
            service.saveUser(new AppUser(null, "Safa", "Leventoglu", "leventoglu@gmail.com", "leventoglu", "1234", new ArrayList<>()));
            service.saveUser(new AppUser(null, "Özgür", "Okumuş", "ozgurokumus@gmail.com", "ozgurokumus", "1234", new ArrayList<>()));

            service.assignRoleToUser("nikolaDrljaca", RoleUtil.ROLE_ADMIN);
            service.assignRoleToUser("kayrauckilinc", RoleUtil.ROLE_ADMIN);
            service.assignRoleToUser("davutkulaksiz", RoleUtil.ROLE_ADMIN);
            service.assignRoleToUser("leventoglu", RoleUtil.ROLE_ADMIN);
            service.assignRoleToUser("ozgurokumus", RoleUtil.ROLE_ADMIN);
        };
    }
}
