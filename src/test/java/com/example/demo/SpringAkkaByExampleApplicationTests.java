package com.example.demo;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.util.Timeout;
import com.example.demo.actor.GreetingActor;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import scala.concurrent.Await;
import scala.concurrent.Future;
import scala.concurrent.duration.FiniteDuration;

import java.util.concurrent.TimeUnit;

import static akka.pattern.Patterns.ask;
import static com.example.demo.configuration.SpringExtension.SPRING_EXTENSION_PROVIDER;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringAkkaByExampleApplicationTests {

    @Autowired
    private ActorSystem actorSystem;

    @Test
    public void contextLoads() {
    }


    @Test
    public void testAkkaSpringExample() throws Exception {
        ActorRef actorRef = actorSystem.actorOf(SPRING_EXTENSION_PROVIDER
                .get(actorSystem).props("greetingActor"), "greet");

        FiniteDuration finiteDuration = FiniteDuration.create(1, TimeUnit.SECONDS);
        Timeout timeout = Timeout.durationToTimeout(finiteDuration);
        Future<Object> objectFuture = ask(actorRef, new GreetingActor.Greet("Hung"), timeout);

        System.out.println(Await.result(objectFuture, finiteDuration));

        Assert.assertEquals("Hello, Hung", Await.result(objectFuture, finiteDuration));
    }
}
