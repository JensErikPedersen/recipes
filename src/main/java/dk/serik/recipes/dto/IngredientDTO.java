package dk.serik.recipes.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import dk.serik.recipes.model.RecipeIngredient;
import lombok.Builder;
import lombok.Getter;

import java.time.OffsetDateTime;
import java.util.Set;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Getter
public class IngredientDTO extends BaseDTO {
    private String name;
    private String description;
    private Set<RecipeIngredientDTO> recipeIngredients;

    @Builder
    public IngredientDTO(String id, OffsetDateTime created, String createdBy, OffsetDateTime updated, String updatedBy, String name, String description, Set<RecipeIngredientDTO> recipeIngredients) {
        super(id, created, createdBy, updated, updatedBy);
        this.name = name;
        this.description = description;
        this.recipeIngredients = recipeIngredients;
    }

}
