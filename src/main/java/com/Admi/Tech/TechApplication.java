package com.Admi.Tech;

import com.Admi.Tech.Modelo.Empresa;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
public class TechApplication {
	@GetMapping ("/test")
	public String test() {
		Empresa emp = new Empresa("Santiago SAS", "Calle 100", "2351397", "8032542-4");
		emp.setNombre("SOL LTDA");
		return emp.getNombre();
	}

	public static void main(String[] args) {
		SpringApplication.run(TechApplication.class, args);
	}

}
