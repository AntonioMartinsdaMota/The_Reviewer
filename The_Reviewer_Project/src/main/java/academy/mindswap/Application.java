package academy.mindswap;

import academy.mindswap.services.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
@EnableAsync
@EnableAspectJAutoProxy(proxyTargetClass=true)
public class Application {

	public static void main(String[] args) {

		SpringApplication.run(Application.class, args);
				//.start();
	}

	@Bean
	CommandLineRunner run(UserService userService) {
		return args -> {
			userService.createRoles();
			userService.createOwner();
		};
	}

	@Bean
	PasswordEncoder passwordEncoder(){
		return new BCryptPasswordEncoder();
	}



}
