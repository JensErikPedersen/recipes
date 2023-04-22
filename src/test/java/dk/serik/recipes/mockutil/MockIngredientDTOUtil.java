package dk.serik.recipes.mockutil;

import dk.serik.recipes.dto.IngredientDTO;
import dk.serik.recipes.testutil.OffsetDateTimeProvider;

import java.util.HashSet;
import java.util.Set;

public class MockIngredientDTOUtil {

    public static IngredientDTO mockIngredientHvedemelDTO() {
        IngredientDTO mock = IngredientDTO.builder()
                .id("12345_hvedemel")
                .name("Hvedemel")
                .description("Sigtet hvedemel uden skalder og kim")
                .createdBy("Majken")
                .updatedBy("Jens")
                .updated(OffsetDateTimeProvider.provide("2023-01-25T14:25:15"))
                .created(OffsetDateTimeProvider.provide("2022-11-05T19:47:29"))
                .build();
        return mock;
    }

    public static IngredientDTO mockIngredientGaerDTO() {
        IngredientDTO mock = IngredientDTO.builder()
                .id("12345_gaer")
                .name("Gær")
                .description("Alm. gær")
                .createdBy("Majken")
                .updatedBy("Jens")
                .updated(OffsetDateTimeProvider.provide("2023-01-25T14:25:15"))
                .created(OffsetDateTimeProvider.provide("2022-11-05T19:47:29"))
                .build();
        return mock;
    }

    public static IngredientDTO mockIngredientSaltDTO() {
        IngredientDTO mock = IngredientDTO.builder()
                .id("12345_salt")
                .name("Salt")
                .description("Havsalt")
                .createdBy("Majken")
                .updatedBy("Jens")
                .updated(OffsetDateTimeProvider.provide("2023-01-25T14:25:15"))
                .created(OffsetDateTimeProvider.provide("2022-11-05T19:47:29"))
                .build();
        return mock;
    }

    public static IngredientDTO mockIngredientSurdejDTO() {
        IngredientDTO mock = IngredientDTO.builder()
                .id("12345_surdej")
                .name("Surdej")
                .description("Tynd surdej til hvede brød")
                .createdBy("Majken")
                .updatedBy("Jens")
                .updated(OffsetDateTimeProvider.provide("2023-01-25T14:25:15"))
                .created(OffsetDateTimeProvider.provide("2022-11-05T19:47:29"))
                .build();
        return mock;
    }

    public static IngredientDTO mockIngredientWaterDTO() {
        IngredientDTO mock = IngredientDTO.builder()
                .id("12345_vand")
                .name("Vand")
                .description("Postevand")
                .createdBy("Majken")
                .updatedBy("Jens")
                .updated(OffsetDateTimeProvider.provide("2023-01-25T14:25:15"))
                .created(OffsetDateTimeProvider.provide("2022-11-05T19:47:29"))
                .build();
        return mock;
    }

    public static Set<IngredientDTO> mockIngredientDTOs() {
        Set<IngredientDTO> dtos = new HashSet<>();
        dtos.add(mockIngredientWaterDTO());
        dtos.add(mockIngredientSurdejDTO());
        dtos.add(mockIngredientSaltDTO());
        dtos.add(mockIngredientGaerDTO());
        dtos.add(mockIngredientHvedemelDTO());
        return dtos;
    }

}
