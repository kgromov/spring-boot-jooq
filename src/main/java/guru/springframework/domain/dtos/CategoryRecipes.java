package guru.springframework.domain.dtos;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CategoryRecipes {
    private Long id;
    private String description;
    private Long recipes;
}
