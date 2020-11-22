package guru.springframework.domain.dtos;

import guru.springframework.domain.Difficulty;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RecipeNotes {
    private Long id;
    private String description;
    private String recipeNotes;
    private Difficulty difficulty;
}
