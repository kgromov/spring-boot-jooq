package guru.springframework.repositories;

import guru.springframework.domain.Category;
import guru.springframework.services.profiling.Profiling;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;
import java.util.concurrent.TimeUnit;

/**
 * Created by jt on 6/13/17.
 */
public interface CategoryRepository extends CrudRepository<Category, Long> {

    @Profiling(timeunit = TimeUnit.MICROSECONDS)
    Optional<Category> findByDescription(String description);
}
