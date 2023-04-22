package dk.serik.recipes.mapper;

import dk.serik.recipes.dto.RecipeRatingDTO;
import dk.serik.recipes.model.RecipeRating;
import org.mapstruct.Mapper;

@Mapper
public interface RecipeRatingMapper {
    RecipeRating recipeRatingDTOToRecipeRating(RecipeRatingDTO recipeRatingDTO);
    RecipeRatingDTO recipeRatingToRecipeRatingDTO(RecipeRating recipeRating);
}
