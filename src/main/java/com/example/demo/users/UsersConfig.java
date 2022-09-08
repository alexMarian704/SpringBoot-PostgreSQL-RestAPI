package com.example.demo.users;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class UsersConfig {
    @Bean
    CommandLineRunner commandLineRunner(UsersRepository repository){
        return args -> {
           Users alex = new Users("alex@salut.com" ,"Alex","Salut",20);
            Users andrei = new Users("andrei@aa.com" ,"Andrei","Aaa",21);

            repository.saveAll(
                    List.of(alex,andrei)
            );
        };
    }
}
