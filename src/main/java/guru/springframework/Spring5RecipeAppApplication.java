package guru.springframework;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class Spring5RecipeAppApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(Spring5RecipeAppApplication.class, args);
    }
}
