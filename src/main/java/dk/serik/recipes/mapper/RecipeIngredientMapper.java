package dk.serik.recipes.mapper;

import dk.serik.recipes.dto.RecipeIngredientDTO;
import dk.serik.recipes.model.RecipeIngredient;

import java.util.Objects;

public class RecipeIngredientMapper {
    public static RecipeIngredientDTO from(RecipeIngredient entity) {
        if(Objects.isNull(entity)) {
            return null;
        }
        RecipeIngredientDTO dto = RecipeIngredientDTO.builder()
                .ingredientId(entity.getIngredient().getId())
                .recipeId(entity.getRecipe().getId())
                .ingredientName(entity.getIngredient().getName())
                .amount(entity.getAmount())
                .unitLabel(entity.getUnit().getLabel())
                .unitId(entity.getUnit().getId())
                .createdBy(entity.getCreatedBy())
                .created(entity.getCreated())
                .updated(entity.getUpdated())
                .updatedBy(entity.getUpdatedBy())
                .build();
        return dto;
    }
}
