package dk.serik.recipes.mapper;

import dk.serik.recipes.dto.IngredientDTO;
import dk.serik.recipes.model.Ingredient;
import dk.serik.recipes.mockutil.MockIngredientDTOUtil;
import dk.serik.recipes.mockutil.MockIngredientUtil;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

public class IngredientMapperTest {
    private IngredientMapper mapper = Mappers.getMapper(IngredientMapper.class);

    @Test
    @DisplayName("Given Category is OK, When Map to DTO, Then Ok")
    public void passMapperFromValidEntityToDto() {
        IngredientDTO mappedDto = mapper.ingredientToIngredientDTO(MockIngredientUtil.mockHvedemel());
        Assertions.assertThat(mappedDto).isNotNull();
        Assertions.assertThat(mappedDto).isEqualTo(MockIngredientDTOUtil.mockIngredientHvedemelDTO());
    }

    @Test
    @DisplayName("Given CategoryDTO is OK, When Map to Entity, Then Ok")
    public void passMapperFromValidDtoToEntity() {
        Ingredient mappedTo = mapper.ingredientDTOToIngredient(MockIngredientDTOUtil.mockIngredientHvedemelDTO());
        Assertions.assertThat(mappedTo).isNotNull();
        Assertions.assertThat(mappedTo.getName()).isEqualTo(MockIngredientUtil.mockHvedemel().getName());
        Assertions.assertThat(mappedTo.getDescription()).isEqualTo(MockIngredientUtil.mockHvedemel().getDescription());
    }

    @Test
    @DisplayName("Given Unit is null, When mapped to DTO, Then DTO is Null")
    public void passMapperFromNullEntityToNullDto() {
       IngredientDTO mappedDto = mapper.ingredientToIngredientDTO(null);
        Assertions.assertThat(mappedDto).isNull();
    }

    @Test
    @DisplayName("Given UnitDTO is null, When mapped to Entity, Then Entity is Null")
    public void passMapperFromNullDtoToNullEntity() {
        Ingredient mappedDto = mapper.ingredientDTOToIngredient(null);
        Assertions.assertThat(mappedDto).isNull();
    }

}
