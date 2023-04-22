package dk.serik.recipes.mockutil;

import dk.serik.recipes.dto.RecipeDTO;
import dk.serik.recipes.testutil.OffsetDateTimeProvider;

public class MockRecipeDTOUtil {

    public static RecipeDTO mockRecipeHvedeBreadDTO() {
        RecipeDTO dto = RecipeDTO.builder()
                .category(MockCategoryDTOUtil.mockCategoryDTO())
                .id("987645_recipe")
                .recipeIngredients(MockRecipeIngredientDTOUtil.mockRecipeIngredientDTOS())
                .recipeRatings(MockRecipeRatingDTOUtil.mockRecipeRatingDTOs())
                .tags(MockTagUtil.mockTagDtos())
                .id("12345_hvedebroed")
                .description("Lækkert brød til morgenmad eller til kaffen med ost på")
                .name("Hvedebrød")
                .createdBy("Majken")
                .updatedBy("Jens")
                .updated(OffsetDateTimeProvider.provide("2023-01-25T14:25:15"))
                .created(OffsetDateTimeProvider.provide("2022-11-05T19:47:29"))
                .instructions("1. Hæld vand og gær i kedel. 2. Rør..")
                .build();
        return dto;
    }
}
