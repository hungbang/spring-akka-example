package com.example.demo.configuration;

import akka.actor.Actor;
import akka.actor.IndirectActorProducer;
import org.springframework.context.ApplicationContext;

/**
 * Created by KAI on 5/1/18.
 */
public class SpringActorProducer implements IndirectActorProducer {

    private ApplicationContext applicationContext;

    private String actorBeanName;


    public SpringActorProducer(ApplicationContext applicationContext, String actorBeanName) {
        this.applicationContext = applicationContext;
        this.actorBeanName = actorBeanName;
    }

    @Override
    public Actor produce() {
        return (Actor) applicationContext.getBean(actorBeanName);
    }

    @Override
    public Class<? extends Actor> actorClass() {
        return (Class<? extends Actor>) applicationContext.getType(actorBeanName);
    }
}
