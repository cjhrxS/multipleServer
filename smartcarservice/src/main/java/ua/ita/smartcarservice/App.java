package ua.ita.smartcarservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication(scanBasePackageClasses = App.class)
@EnableScheduling
public class App {
	public static void main(String[] args) {

		SpringApplication.run(App.class, args);
	}
}
