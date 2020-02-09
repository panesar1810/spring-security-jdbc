package code.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan(basePackages = "code.*")
@SpringBootApplication
public class SecurityTemplate {

	public static void main(String[] args) {
		System.setProperty("Spring.main.lazy-initialization", "true");
		SpringApplication.run(SecurityTemplate.class, args);
	}

}
