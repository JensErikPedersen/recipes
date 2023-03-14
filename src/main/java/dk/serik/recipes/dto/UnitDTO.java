package dk.serik.recipes.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import dk.serik.recipes.model.RecipeIngredient;
import lombok.Builder;
import lombok.Getter;

import java.time.OffsetDateTime;
import java.util.Set;
@JsonInclude(JsonInclude.Include.NON_NULL)
@Getter
public class UnitDTO extends GenericDTO {

    private String label;

    private String description;

    private Set<RecipeIngredient> recipeIngredientEntities;

    @Builder
    public UnitDTO(String id, OffsetDateTime created, String createdBy, OffsetDateTime updated, String updatedBy, String label, String description, Set<RecipeIngredient> recipeIngredientEntities) {
        super(id, created, createdBy, updated, updatedBy);
        this.label = label;
        this.description = description;
        this.recipeIngredientEntities = recipeIngredientEntities;
    }
}
