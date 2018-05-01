package com.example.demo.configuration;

import akka.actor.AbstractExtensionId;
import akka.actor.ExtendedActorSystem;
import akka.actor.Extension;
import akka.actor.Props;
import org.springframework.context.ApplicationContext;

/**
 * Created by KAI on 5/1/18.
 */
public class SpringExtension extends AbstractExtensionId<SpringExtension.SpringExt> {


    public static final SpringExtension SPRING_EXTENSION_PROVIDER = new SpringExtension();

    @Override
    public SpringExt createExtension(ExtendedActorSystem extendedActorSystem) {
        return new SpringExt();
    }

    public static class SpringExt implements Extension {

        private volatile ApplicationContext applicationContext;

        public void initialize(ApplicationContext applicationContext){
            this.applicationContext = applicationContext;
        }

        public Props props(String actorBeanName){
            return Props.create(SpringActorProducer.class, applicationContext, actorBeanName);
        }
    }
}
