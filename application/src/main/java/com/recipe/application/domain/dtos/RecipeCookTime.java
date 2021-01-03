package com.recipe.application.domain.dtos;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RecipeCookTime {
    private Long id;
    private String description;
    private Integer prepTime;
    private Integer cookTime;
}
