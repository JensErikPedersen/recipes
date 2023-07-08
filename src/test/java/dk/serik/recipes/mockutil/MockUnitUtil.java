package dk.serik.recipes.mockutil;

import dk.serik.recipes.dto.UnitDTO;
import dk.serik.recipes.model.Unit;
import dk.serik.recipes.testutil.OffsetDateTimeProvider;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.UUID;

public class MockUnitUtil {

    public static Unit mockGram() {
        Unit mock = new Unit();
        mock.setDescription("Gram");
        mock.setName("g");
        mock.setCreatedBy("Majken");
        mock.setUpdatedBy("Jens");
        ReflectionTestUtils.setField(mock, "id", UUID.fromString("f7823293-7874-4459-9fb7-6b420a0627fa"));
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
        ReflectionTestUtils.setField(mock, "id", UUID.fromString("046d0928-0806-480b-ad8b-c6845e99643b"));
        ReflectionTestUtils.setField(mock, "updated", OffsetDateTimeProvider.provide("2023-01-25T14:25:15"));
        ReflectionTestUtils.setField(mock, "created", OffsetDateTimeProvider.provide("2022-11-05T19:47:29"));
        return mock;
    }

    public static UnitDTO mockUnitDeciliterDTO() {
        UnitDTO mock = UnitDTO.builder()
                .id("046d0928-0806-480b-ad8b-c6845e99643b")
                .createdBy("Majken")
                .created(OffsetDateTimeProvider.provide("2022-11-05T19:47:29"))
                .updatedBy("Jens")
                .updated(OffsetDateTimeProvider.provide("2023-01-25T14:25:15"))
                .name("dl")
                .description("Deciliter")
                .build();
        return mock;
    }

    public static UnitDTO mockUnitGramDTO() {
        UnitDTO mock = UnitDTO.builder()
                .id("441a657c-d4bb-4ff0-8e76-38325dbf03cb")
                .createdBy("Majken")
                .created(OffsetDateTimeProvider.provide("2022-11-05T19:47:29"))
                .updatedBy("Jens")
                .updated(OffsetDateTimeProvider.provide("2023-01-25T14:25:15"))
                .name("g")
                .description("Gram")
                .build();
        return mock;
    }
}
