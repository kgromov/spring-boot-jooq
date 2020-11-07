package guru.springframework.services.profiling;

import org.springframework.boot.logging.LogLevel;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.concurrent.TimeUnit;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.METHOD})
public @interface Profiling {

    LogLevel loglevel() default LogLevel.DEBUG;

    TimeUnit timeunit() default TimeUnit.MILLISECONDS;
}
