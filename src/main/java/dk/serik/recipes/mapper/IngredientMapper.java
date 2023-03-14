package dk.serik.recipes.mapper;

import dk.serik.recipes.dto.IngredientDTO;
import dk.serik.recipes.model.Ingredient;
import org.mapstruct.Mapper;


@Mapper
public interface IngredientMapper {
    Ingredient ingredientDTO(IngredientDTO ingredientDTO);
    IngredientDTO ingredient(Ingredient ingredient);
}
