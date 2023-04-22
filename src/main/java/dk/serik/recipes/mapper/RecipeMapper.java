package dk.serik.recipes.mapper;

import dk.serik.recipes.dto.RecipeDTO;
import dk.serik.recipes.model.Recipe;
import org.mapstruct.Mapper;

@Mapper
public interface RecipeMapper {
    Recipe recipeDtoToRecipe(RecipeDTO recipeDTO);
    RecipeDTO recipeToRecipeDTO(Recipe recipe);
}
