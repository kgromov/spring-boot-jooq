package guru.springframework;

import guru.springframework.jooq.demo.JooqDemo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class Spring5RecipeAppApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(Spring5RecipeAppApplication.class, args);
		JooqDemo jooqDemo = context.getBean(JooqDemo.class);
		jooqDemo.testSelectWithJoin();
	}
}
