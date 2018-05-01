package com.example.demo.actor;

import akka.actor.AbstractActor;
import akka.japi.pf.ReceiveBuilder;
import com.example.demo.service.GreetingService;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import scala.PartialFunction;
import scala.runtime.BoxedUnit;

/**
 * Created by KAI on 5/1/18.
 */
@Component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class GreetingActor extends AbstractActor {

    private GreetingService greetingService;

    public GreetingActor(GreetingService greetingService) {
        this.greetingService = greetingService;
    }

    @Override
    public PartialFunction<Object, BoxedUnit> receive() {
        return ReceiveBuilder.match(Greet.class, greet -> sender().tell(greetingService.greet(greet.getName()), self())).matchAny(o -> unhandled(o)).build();
    }


    public static class Greet {

        private String name;

        public Greet(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

    }
}
