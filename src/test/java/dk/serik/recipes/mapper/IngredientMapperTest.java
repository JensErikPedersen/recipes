package dk.serik.recipes.mapper;

import dk.serik.recipes.dto.IngredientDTO;
import dk.serik.recipes.model.Ingredient;
import dk.serik.recipes.testutil.OffsetDateTimeProvider;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import org.springframework.test.util.ReflectionTestUtils;

public class IngredientMapperTest {
    private IngredientMapper mapper = Mappers.getMapper(IngredientMapper.class);

    @Test
    @DisplayName("Given Category is OK, When Map to DTO, Then Ok")
    public void passMapperFromValidEntityToDto() {
        IngredientDTO mappedDto = mapper.ingredient(mockIngredient());
        Assertions.assertThat(mappedDto).isNotNull();
        Assertions.assertThat(mappedDto).isEqualTo(mockIngredientDTO());
    }

    @Test
    @DisplayName("Given CategoryDTO is OK, When Map to Entity, Then Ok")
    public void passMapperFromValidDtoToEntity() {
        Ingredient mappedTo = mapper.ingredientDTO((mockIngredientDTO()));
        Assertions.assertThat(mappedTo).isNotNull();
        Assertions.assertThat(mappedTo.getName()).isEqualTo(mockIngredient().getName());
        Assertions.assertThat(mappedTo.getDescription()).isEqualTo(mockIngredient().getDescription());
    }

    @Test
    @DisplayName("Given Unit is null, When mapped to DTO, Then DTO is Null")
    public void passMapperFromNullEntityToNullDto() {
       IngredientDTO mappedDto = mapper.ingredient(null);
        Assertions.assertThat(mappedDto).isNull();
    }

    @Test
    @DisplayName("Given UnitDTO is null, When mapped to Entity, Then Entity is Null")
    public void passMapperFromNullDtoToNullEntity() {
        Ingredient mappedDto = mapper.ingredientDTO(null);
        Assertions.assertThat(mappedDto).isNull();
    }

    private Ingredient mockIngredient() {
        Ingredient mock = new Ingredient();
        mock.setName("Hvedemel");
        mock.setDescription("Sigtet hvedemel uden skalder og kim");
        mock.setCreatedBy("Majken");
        mock.setUpdatedBy("Jens");
        ReflectionTestUtils.setField(mock, "id", "12345");
        ReflectionTestUtils.setField(mock, "updated", OffsetDateTimeProvider.provide("2023-01-25T14:25:15"));
        ReflectionTestUtils.setField(mock, "created", OffsetDateTimeProvider.provide("2022-11-05T19:47:29"));
        return mock;
    }

    private IngredientDTO mockIngredientDTO() {
        IngredientDTO mock = IngredientDTO.builder()
                .id("12345")
                .name("Hvedemel")
                .description("Sigtet hvedemel uden skalder og kim")
                .createdBy("Majken")
                .updatedBy("Jens")
                .updated(OffsetDateTimeProvider.provide("2023-01-25T14:25:15"))
                .created(OffsetDateTimeProvider.provide("2022-11-05T19:47:29"))
                .build();
        return mock;
    }
}
