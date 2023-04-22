package dk.serik.recipes.mapper;

import org.mapstruct.factory.Mappers;

public class RecipientIngredientMapperTest {

    private RecipeIngredientMapper mapper = Mappers.getMapper(RecipeIngredientMapper.class);

//    @Test
//    @DisplayName("Given valid entity, When mapped by mapper, Then DTO is Ok")
//    public void passMapperFromValidEntityToDto() {
//        RecipeIngredientDTO mappedDto = mapper.recipeIngredient(MockRecipeIngredientUtil.mockRecipeIngredientSalt());
//        Assertions.assertThat(mappedDto).isNotNull();
//        Assertions.assertThat(mappedDto).isEqualTo(mockRatingDTO());
//    }

//
//    private RecipeIngredientDTO mockRecipeIngredientDTO() {
//        RecipeIngredientDTO mock = RecipeIngredientDTO.builder()
//                .ingredient(mockIngredientDTO())
//                .amount(new BigDecimal(16))
//                .recipe(MockRecipeUtil.mockRecipeHvedebread())
//    }
//
//    private IngredientDTO mockIngredientDTO() {
//        IngredientDTO mock = IngredientDTO.builder()
//                .id("12345_hvedemel")
//                .name("Hvedemel")
//                .description("Sigtet hvedemel uden skalder og kim")
//                .createdBy("Majken")
//                .updatedBy("Jens")
//                .updated(OffsetDateTimeProvider.provide("2023-01-25T14:25:15"))
//                .created(OffsetDateTimeProvider.provide("2022-11-05T19:47:29"))
//                .build();
//        return mock;
//    }
//
//    private RecipeDTO mockRecipeDTO() {
//        RecipeDTO dto = RecipeDTO.builder()
//                .category(mockCategoryDTO())
//                .r
//    }
//
//
//    private Set<RatingDTO> mockRatingDTO() {
//        RatingDTO dto = RatingDTO.builder()
//                .id("56789_rating5")
//                .rating(5)
//                .description("Outstanding")
//                .createdBy("Majken")
//                .updatedBy("Jens")
//                .updated(OffsetDateTimeProvider.provide("2023-01-25T14:25:15"))
//                .created(OffsetDateTimeProvider.provide("2022-11-05T19:47:29"))
//                .build();
//        Set<RatingDTO> dtos = new HashSet<>();
//        dtos.add(dto);
//        return dtos;
//    }




}
