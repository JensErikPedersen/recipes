package dk.serik.recipes.mockutil;

import dk.serik.recipes.dto.RecipeIngredientDTO;
import dk.serik.recipes.testutil.OffsetDateTimeProvider;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

public class MockRecipeIngredientDTOUtil {

    public static Set<RecipeIngredientDTO> mockRecipeIngredientDTOS() {
        Set<RecipeIngredientDTO> dtos = new HashSet<>();
        dtos.add(mockRecipeIngredientWaterDTO());
        dtos.add(mockRecipeIngredientSurdejDTO());
        dtos.add(mockRecipeIngredientGaerDTO());
        dtos.add(mockRecipeIngredientSaltDTO());
        dtos.add(mockRecipeIngredientHvedemelDTO());
        return dtos;
    }

    public static RecipeIngredientDTO mockRecipeIngredientSaltDTO() {
        return RecipeIngredientDTO.builder()
                .recipe(MockRecipeDTOUtil.mockRecipeHvedeBreadDTO())
                .unit(MockUnitUtil.mockUnitGramDTO())
                .amount(new BigDecimal(16))
                .ingredient(MockIngredientDTOUtil.mockIngredientSaltDTO())
                .created(OffsetDateTimeProvider.provide("2022-12-29T15:47:29"))
                .updated(OffsetDateTimeProvider.provide("2023-03-06T14:43:18"))
                .createdBy("Majken")
                .updatedBy("Jens")
                .id("56789_salt")
                .build();
    }

    public static RecipeIngredientDTO mockRecipeIngredientHvedemelDTO() {
        return RecipeIngredientDTO.builder()
                .recipe(MockRecipeDTOUtil.mockRecipeHvedeBreadDTO())
                .unit(MockUnitUtil.mockUnitGramDTO())
                .amount(new BigDecimal(550))
                .ingredient(MockIngredientDTOUtil.mockIngredientHvedemelDTO())
                .created(OffsetDateTimeProvider.provide("2022-12-15T15:47:29"))
                .updated(OffsetDateTimeProvider.provide("2023-03-12T14:43:18"))
                .createdBy("Majken")
                .updatedBy("Jens")
                .id("56789_hvedemel")
                .build();
    }
    public static RecipeIngredientDTO mockRecipeIngredientSurdejDTO() {
        return RecipeIngredientDTO.builder()
                .recipe(MockRecipeDTOUtil.mockRecipeHvedeBreadDTO())
                .unit(MockUnitUtil.mockUnitDeciliterDTO())
                .amount(new BigDecimal(1))
                .ingredient(MockIngredientDTOUtil.mockIngredientHvedemelDTO())
                .created(OffsetDateTimeProvider.provide("2022-12-15T15:47:29"))
                .updated(OffsetDateTimeProvider.provide("2023-03-12T14:43:18"))
                .createdBy("Majken")
                .updatedBy("Jens")
                .id("56789_surdej")
                .build();
    }
    public static RecipeIngredientDTO mockRecipeIngredientWaterDTO() {
        return RecipeIngredientDTO.builder()
                .recipe(MockRecipeDTOUtil.mockRecipeHvedeBreadDTO())
                .unit(MockUnitUtil.mockUnitDeciliterDTO())
                .amount(new BigDecimal(5))
                .ingredient(MockIngredientDTOUtil.mockIngredientHvedemelDTO())
                .created(OffsetDateTimeProvider.provide("2022-12-15T15:47:29"))
                .updated(OffsetDateTimeProvider.provide("2023-03-12T14:43:18"))
                .createdBy("Majken")
                .updatedBy("Jens")
                .id("56789_vand")
                .build();
    }

    public static RecipeIngredientDTO mockRecipeIngredientGaerDTO() {
        return RecipeIngredientDTO.builder()
                .recipe(MockRecipeDTOUtil.mockRecipeHvedeBreadDTO())
                .unit(MockUnitUtil.mockUnitGramDTO())
                .amount(new BigDecimal(10))
                .ingredient(MockIngredientDTOUtil.mockIngredientHvedemelDTO())
                .created(OffsetDateTimeProvider.provide("2022-12-15T15:47:29"))
                .updated(OffsetDateTimeProvider.provide("2023-03-12T14:43:18"))
                .createdBy("Majken")
                .updatedBy("Jens")
                .id("56789_gaer")
                .build();
    }
}
