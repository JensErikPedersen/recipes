package dk.serik.recipes.mockutil;

import dk.serik.recipes.dto.RecipeRatingDTO;
import dk.serik.recipes.testutil.OffsetDateTimeProvider;

import java.util.HashSet;
import java.util.Set;

public class MockRecipeRatingDTOUtil {
    public static RecipeRatingDTO mockRecipeHvedebreadRatingDTO1() {
        return RecipeRatingDTO.builder()
                .description("Den er i skabet")
                .rating(4)
                .recipeId("56789_rating4")
                .id("34567_hvedebroed_rating1")
                .created(OffsetDateTimeProvider.provide("2022-11-05T19:47:29"))
                .createdBy("Majken")
                .build();
    }

    public static RecipeRatingDTO mockRecipeHvedebreadRatingDTO2() {
        return RecipeRatingDTO.builder()
                .description("Den er perfekt")
                .rating(5)
                .recipeId("56789_rating4")
                .id("34567_hvedebroed_rating2")
                .created(OffsetDateTimeProvider.provide("2022-11-05T19:47:29"))
                .createdBy("Jens")
                .build();
    }

    public static RecipeRatingDTO mockRecipeHvedebreadRatingDTO3() {
        return RecipeRatingDTO.builder()
                .description("Skønt")
                .rating(5)
                .recipeId("56789_rating4")
                .id("34567_hvedebroed_rating2")
                .created(OffsetDateTimeProvider.provide("2022-11-05T19:47:29"))
                .createdBy("Oskar")
                .build();
    }

    public static Set<RecipeRatingDTO> mockRecipeRatingDTOs() {
        Set<RecipeRatingDTO> dtos = new HashSet<>();
        dtos.add(mockRecipeHvedebreadRatingDTO1());
        dtos.add(mockRecipeHvedebreadRatingDTO2());
        dtos.add(mockRecipeHvedebreadRatingDTO3());
        return dtos;
    }
}
