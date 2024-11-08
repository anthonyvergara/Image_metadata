package com.metadados;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@EntityScan(basePackages = "com.metadados.model")
@ComponentScan(basePackages = {"com.metadados.*"}) //Realizar injeção de dependências.
@EnableJpaRepositories(basePackages = {"com.metadados.repository"}) // Habilita a funcionalidade das interfaces de persistência.
@EnableTransactionManagement// Gerenciador de transações
@RestController
public class MetadadosApplication {

	public static void main(String[] args) {
		SpringApplication.run(MetadadosApplication.class, args);
	}

}
