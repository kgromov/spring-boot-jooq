package com.recipe.application.services.demo;

import com.entities.domain.Difficulty;
import org.jooq.impl.EnumConverter;
import org.springframework.stereotype.Component;

@Component
public class DifficultyConverter extends EnumConverter<String, Difficulty> {

    public DifficultyConverter() {
        super(String.class, Difficulty.class);
    }
}
