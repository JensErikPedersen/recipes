package dk.serik.recipes.mockutil;

import dk.serik.recipes.dto.CategoryDTO;
import dk.serik.recipes.testutil.OffsetDateTimeProvider;

public class MockCategoryDTOUtil {

    public static CategoryDTO mockCategoryDTO() {
        CategoryDTO dto = CategoryDTO.builder()
                .id("12345_bread")
                .name("Brød")
                .description("Brød til alle måltider")
                .createdBy("Majken")
                .updatedBy("Jens")
                .updated(OffsetDateTimeProvider.provide("2023-01-25T14:25:15"))
                .created(OffsetDateTimeProvider.provide("2022-11-05T19:47:29"))
                .build();
        return dto;
    }
}
