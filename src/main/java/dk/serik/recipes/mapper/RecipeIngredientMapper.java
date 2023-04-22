package dk.serik.recipes.mapper;

import dk.serik.recipes.dto.RecipeIngredientDTO;
import dk.serik.recipes.model.RecipeIngredient;
import org.mapstruct.Mapper;

@Mapper
public interface RecipeIngredientMapper {
    RecipeIngredient recipeIngredientDTOToRecipeIngredient(RecipeIngredientDTO recipeIngredientDTO);
    RecipeIngredientDTO recipeIngredientToRecipeIngredientDTO(RecipeIngredient recipeIngredient);
}
