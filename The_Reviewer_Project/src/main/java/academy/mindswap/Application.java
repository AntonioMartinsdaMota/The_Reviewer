package academy.mindswap;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
@EnableAspectJAutoProxy(proxyTargetClass=true)
public class Application {

	public static void main(String[] args) {

		SpringApplication.run(Application.class, args);
				//.start();
	}



}
