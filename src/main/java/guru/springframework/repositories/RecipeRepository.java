package guru.springframework.repositories;

import guru.springframework.domain.Recipe;
import guru.springframework.services.profiling.Profiling;
import org.springframework.data.repository.CrudRepository;

import java.util.concurrent.TimeUnit;

/**
 * Created by jt on 6/13/17.
 */
@Profiling(timeunit = TimeUnit.MICROSECONDS)
public interface RecipeRepository extends CrudRepository<Recipe, Long> {
}
