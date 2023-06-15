package dk.serik.recipes.mockutil;

import dk.serik.recipes.dto.UnitDTO;
import dk.serik.recipes.model.Unit;
import dk.serik.recipes.testutil.OffsetDateTimeProvider;
import org.springframework.test.util.ReflectionTestUtils;

public class MockUnitUtil {

    public static Unit mockGram() {
        Unit mock = new Unit();
        mock.setDescription("Gram");
        mock.setName("g");
        mock.setCreatedBy("Majken");
        mock.setUpdatedBy("Jens");
        ReflectionTestUtils.setField(mock, "id", "12345_gram");
        ReflectionTestUtils.setField(mock, "updated", OffsetDateTimeProvider.provide("2023-01-25T14:25:15"));
        ReflectionTestUtils.setField(mock, "created", OffsetDateTimeProvider.provide("2022-11-05T19:47:29"));
        return mock;
    }

    public static Unit mockDl() {
        Unit mock = new Unit();
        mock.setDescription("Deciliter");
        mock.setName("dl");
        mock.setCreatedBy("Majken");
        mock.setUpdatedBy("Jens");
        ReflectionTestUtils.setField(mock, "id", "12345_deciliter");
        ReflectionTestUtils.setField(mock, "updated", OffsetDateTimeProvider.provide("2023-01-25T14:25:15"));
        ReflectionTestUtils.setField(mock, "created", OffsetDateTimeProvider.provide("2022-11-05T19:47:29"));
        return mock;
    }

    public static UnitDTO mockUnitDeciliterDTO() {
        UnitDTO mock = UnitDTO.builder()
                .id("12345_deciliter")
                .createdBy("Majken")
                .created(OffsetDateTimeProvider.provide("2022-11-05T19:47:29"))
                .updatedBy("Jens")
                .updated(OffsetDateTimeProvider.provide("2023-01-25T14:25:15"))
                .label("dl")
                .description("Deciliter")
                .build();
        return mock;
    }

    public static UnitDTO mockUnitGramDTO() {
        UnitDTO mock = UnitDTO.builder()
                .id("12345_gram")
                .createdBy("Majken")
                .created(OffsetDateTimeProvider.provide("2022-11-05T19:47:29"))
                .updatedBy("Jens")
                .updated(OffsetDateTimeProvider.provide("2023-01-25T14:25:15"))
                .label("g")
                .description("Gram")
                .build();
        return mock;
    }
}
