package br.com.cursoudemy.api.config;

import br.com.cursoudemy.api.domain.User;
import br.com.cursoudemy.api.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.util.List;

@Configuration
@ComponentScan(basePackageClasses = {UserRepository.class})
@Profile("local")
public class LocalConfig {
    @Autowired
    private UserRepository repository;
    @Bean
    public void startDB(){
        User u1 = new User(null, "Luana", "luana@hotmail.com", "1234");
        User u2 = new User(null, "Maria", "maria@hotmail.com", "1234");

        repository.saveAll(List.of(u1,u2));

    }
}
