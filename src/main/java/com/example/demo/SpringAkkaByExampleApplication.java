package com.example.demo;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

import static com.example.demo.configuration.SpringExtension.SPRING_EXTENSION_PROVIDER;

@SpringBootApplication
public class SpringAkkaByExampleApplication {

    @Autowired
    private ApplicationContext applicationContext;

    public static void main(String[] args) {
        SpringApplication.run(SpringAkkaByExampleApplication.class, args);
    }


    @Bean
    public ActorSystem actorSystem() {
        ActorSystem actorSystem = ActorSystem.create("akka-spring-demo");
        SPRING_EXTENSION_PROVIDER.get(actorSystem).initialize(applicationContext);
        return actorSystem;
    }
}
