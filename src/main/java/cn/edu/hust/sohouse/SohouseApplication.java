package cn.edu.hust.sohouse;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class SohouseApplication {

	public static void main(String[] args) {
		SpringApplication.run(SohouseApplication.class, args);
	}

	@GetMapping("/hello")
	public String hello()
	{
		return "hello,world!";
	}
}
