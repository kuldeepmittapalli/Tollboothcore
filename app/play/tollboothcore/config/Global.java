package play.tollboothcore.config;

import javax.inject.Inject;
import javax.inject.Singleton;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import play.inject.ApplicationLifecycle;
import play.libs.F;


@Singleton
public class Global {


    private ConfigurableApplicationContext applicationContext;

    @Inject
    public Global( ApplicationLifecycle lifecycle ) {
        applicationContext =new AnnotationConfigApplicationContext(TollboothSpringConfiguration.class);
        lifecycle.addStopHook( () -> {
            applicationContext.close();
            return F.Promise.pure( null );
        });
    }

}

