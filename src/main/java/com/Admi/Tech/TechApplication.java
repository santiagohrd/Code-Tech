package com.Admi.Tech;

import com.Admi.Tech.Modelo.Empresa;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
public class TechApplication {

	public static void main(String[] args) {
		SpringApplication.run(TechApplication.class, args);
	}

	@GetMapping ("/Empresa")
	public String test(){
		Empresa emp = new Empresa("Santiago SAS", "Calle 100", "2351397","8032542-4");
		//emp.setNombre("SOLAR LTDA");
		return emp.getNombre();
	}
}
