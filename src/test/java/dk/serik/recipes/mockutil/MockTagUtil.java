package dk.serik.recipes.mockutil;

import dk.serik.recipes.dto.TagDTO;
import dk.serik.recipes.model.Tag;
import dk.serik.recipes.testutil.OffsetDateTimeProvider;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.HashSet;
import java.util.Set;

public class MockTagUtil {

    public static Tag mockTag() {
        Tag mock = new Tag();
        mock.setLabel("Sødt");
        mock.setCreatedBy("Majken");
        mock.setUpdatedBy("Jens");
        ReflectionTestUtils.setField(mock, "id", "12345");
        ReflectionTestUtils.setField(mock, "updated", OffsetDateTimeProvider.provide("2023-01-25T14:25:15"));
        ReflectionTestUtils.setField(mock, "created", OffsetDateTimeProvider.provide("2022-11-05T19:47:29"));
        return mock;
    }

    public static TagDTO mockTagDTO() {
        TagDTO mock = TagDTO.builder()
                .id("12345")
                .createdBy("Majken")
                .created(OffsetDateTimeProvider.provide("2022-11-05T19:47:29"))
                .updatedBy("Jens")
                .updated(OffsetDateTimeProvider.provide("2023-01-25T14:25:15"))
                .label("Sødt")
                .build();
        return mock;
    }

    public static Set<TagDTO> mockTagDtos() {
        Set<TagDTO> dtos = new HashSet<>();
        dtos.add(mockTagDTO());

        TagDTO mock = TagDTO.builder()
                .id("98765")
                .createdBy("Jens")
                .created(OffsetDateTimeProvider.provide("2022-12-55T19:47:29"))
                .updatedBy("Majken")
                .updated(OffsetDateTimeProvider.provide("2023-03-25T14:25:15"))
                .label("Mums")
                .build();

        dtos.add(mock);
        return dtos;
    }
}
