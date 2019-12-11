package com.prolagos.sispcbackend;

import java.util.TimeZone;

import javax.annotation.PostConstruct;

//import java.util.Arrays;

//import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

//import com.prolagos.sispcbackend.domain.Ind_Precos;
//import com.prolagos.sispcbackend.repositories.PrecosRepository;


@SpringBootApplication
public class SispcBackendApplication extends SpringBootServletInitializer  {    //usado na instanciação de Objetos  - implements CommandLineRunner 
	
	//@Autowired
	//private PrecosRepository precosRepository;
	
	@PostConstruct
    void started() {
      TimeZone.setDefault(TimeZone.getTimeZone("TimeZone"));
    }

	public static void main(String[] args) {
		SpringApplication.run(SispcBackendApplication.class, args);
	}
	
	/* instanciando objetos
	
	@Override
	public void run(String... args) throws Exception {
		
		Ind_Precos preco1 = new Ind_Precos(1, "Pac(Sulfato)", 0.74);
		Ind_Precos preco2 = new Ind_Precos(2, "Fluor", 0.68);
		Ind_Precos preco3 = new Ind_Precos(3,  "Cal", 0.63);
		Ind_Precos preco4 = new Ind_Precos(4,  "Cloro", 4.90);
		Ind_Precos preco5 = new Ind_Precos(5,  "Polimero", 10.40);
		Ind_Precos preco6 = new Ind_Precos(6,  "Sal", 0.83);
		

		precosRepository.saveAll(Arrays.asList(preco1, preco2, preco3, preco4, preco5, preco6));
		
	}    */
	
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(SispcBackendApplication.class);
	}
	
}
