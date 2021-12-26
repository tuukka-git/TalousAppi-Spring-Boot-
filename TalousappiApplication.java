package com.tuukkal.talousappi;

import com.tuukkal.talousappi.UserManagement.domain.Role;
import com.tuukkal.talousappi.UserManagement.domain.Type;
import com.tuukkal.talousappi.UserManagement.domain.User;
import com.tuukkal.talousappi.UserManagement.repo.TypeRepo;
import com.tuukkal.talousappi.UserManagement.service.TypeService;
import com.tuukkal.talousappi.UserManagement.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;

@SpringBootApplication
public class TalousappiApplication {

	public static void main(String[] args) {
		SpringApplication.run(TalousappiApplication.class, args);
	}

	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	@Bean
	CommandLineRunner run(UserService userService, TypeService typeService) {
		return args -> {
			userService.saveRole(new Role(null, "ROLE_USER"));


			userService.saveUser( new User(null, "testuser1", "1234", new ArrayList<>(), new ArrayList<>()));
			userService.saveUser( new User(null, "testuser2", "1234", new ArrayList<>(), new ArrayList<>()));

			userService.addRoleToUser("testuser1", "ROLE_USER");
			userService.addRoleToUser("testuser2", "ROLE_USER");


			typeService.saveType( new Type(0L,"Muu"));
			typeService.saveType( new Type(1L,"Ruoka"));
			typeService.saveType( new Type(2L,"Liikenne"));
			typeService.saveType( new Type(3L,"Terveys"));
			typeService.saveType( new Type(4L,"Sijoitukset"));
			typeService.saveType( new Type(5L,"Palkka"));
			typeService.saveType( new Type(6L,"Tuki"));


		};
	}

}
