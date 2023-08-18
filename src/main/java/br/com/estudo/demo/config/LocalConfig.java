package br.com.estudo.demo.config;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import br.com.estudo.demo.domain.User;
import br.com.estudo.demo.repositories.UserRepository;

@Configuration
@Profile("local")
public class LocalConfig {

	@Autowired
	private UserRepository repository;
	
	@Bean
	public void startDB() {
		User u1 = new User(null, "Gabriel Nunes", "gabriel@gmail.com", "123");
		User u2 = new User(null, "Fernanda Carluto", "fernanda@gmail.com", "123");
		
		repository.saveAll(List.of(u1, u2));
	}
}
