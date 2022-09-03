package com.Admi.Tech;

import com.Admi.Tech.Modelo.Empresa;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
public class TechApplication {
	@GetMapping ("/test")
	public String test() {
		Empresa Fac = new Empresa("Santiago SAS", "Calle 100", "2351397", "8032542-4");
		Fac.setNombre("SOL LTDA");
		Fac.setTelefono("7869139641");
		Fac.setDireccion("la vieja confiable");
		Fac.setNIT("98752487-8");
		return Fac.getNombre();
	}

	public static void main(String[] args) {
		SpringApplication.run(TechApplication.class, args);
	}

}
