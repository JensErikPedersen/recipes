package dk.serik.recipes.mapper;

import dk.serik.recipes.dto.IngredientDTO;
import dk.serik.recipes.model.Ingredient;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;



@Mapper
public interface IngredientMapper {
    Ingredient ingredientDTOToIngredient(IngredientDTO ingredientDTO);

    @Mapping(target = "recipeIngredients", ignore = true)
    IngredientDTO ingredientToIngredientDTO(Ingredient ingredient);
}
