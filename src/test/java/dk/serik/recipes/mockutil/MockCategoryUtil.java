package dk.serik.recipes.mockutil;

import dk.serik.recipes.dto.CategoryDTO;
import dk.serik.recipes.model.Category;
import dk.serik.recipes.testutil.OffsetDateTimeProvider;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.UUID;

public class MockCategoryUtil {

    public static Category mockBread() {
        Category mock = new Category();
        mock.setName("Brød");
        mock.setDescription("Brød til alle måltider");
        mock.setUpdatedBy("Jens");
        mock.setCreatedBy("Majken");
        ReflectionTestUtils.setField(mock, "id", UUID.fromString("9fc423a3-1bfc-45b0-baed-5cdffe7fd792"));
        ReflectionTestUtils.setField(mock, "updated", OffsetDateTimeProvider.provide("2023-01-25T14:25:15"));
        ReflectionTestUtils.setField(mock, "created", OffsetDateTimeProvider.provide("2022-11-05T19:47:29"));
        return mock;
    }

    public static Category mockSalat() {
        Category mock = new Category();
        mock.setName("Salat");
        mock.setDescription("Salat som tilbehør eller bare sig selv");
        mock.setUpdatedBy("Majken");
        mock.setCreatedBy("Majken");
        ReflectionTestUtils.setField(mock, "id", UUID.fromString("0c9567db-007d-45a9-bc3f-33210730b706"));
        ReflectionTestUtils.setField(mock, "updated", OffsetDateTimeProvider.provide("2023-02-25T14:25:15"));
        ReflectionTestUtils.setField(mock, "created", OffsetDateTimeProvider.provide("2022-12-05T19:47:29"));
        return mock;
    }

    public static CategoryDTO mockCategoryDTO() {
        CategoryDTO dto = CategoryDTO.builder()
                .id(UUID.fromString("9fc423a3-1bfc-45b0-baed-5cdffe7fd792"))
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
