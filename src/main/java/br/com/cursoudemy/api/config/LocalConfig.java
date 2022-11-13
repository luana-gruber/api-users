package br.com.cursoudemy.api.config;

import br.com.cursoudemy.api.domain.User;
import br.com.cursoudemy.api.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.util.List;

@Configuration
@Profile("local")
public class LocalConfig {
    @Autowired
    private UserRepository repository;

    @Bean
    public void startDB(){
        User u1 = new User(1, "Luana", "luana@hotmail.com", "1234");
        User u2 = new User(2, "Maria", "maria@hotmail.com", "1234");

        repository.saveAll(List.of(u1,u2));

    }
}