package guru.springframework.services.demo;

import guru.springframework.domain.Difficulty;
import org.jooq.impl.EnumConverter;
import org.springframework.stereotype.Component;

@Component
public class DifficultyConverter extends EnumConverter<String, Difficulty> {

    public DifficultyConverter() {
        super(String.class, Difficulty.class);
    }
}
