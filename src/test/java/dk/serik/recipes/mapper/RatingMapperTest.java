package dk.serik.recipes.mapper;

import dk.serik.recipes.dto.RatingDTO;
import dk.serik.recipes.model.Rating;
import dk.serik.recipes.testutil.OffsetDateTimeProvider;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.UUID;

public class RatingMapperTest {
    @Test
    @DisplayName("Given valid entity, When mapped by mapper, Then DTO is Ok")
    public void passMapperFromValidEntityToDto() {
        RatingDTO mappedDto = RatingMapper.from(mockRating5());
        Assertions.assertThat(mappedDto).isNotNull();
        Assertions.assertThat(mappedDto).isEqualTo(mockRatingDTO5());
    }

    @Test
    @DisplayName("Given Unit is null, When mapped to DTO, Then DTO is Null")
    public void passMapperFromNullEntityToNullDto() {
        RatingDTO mappedDto = RatingMapper.from(null);
        Assertions.assertThat(mappedDto).isNull();
    }


    private static Rating mockRating5() {
        Rating mock = new Rating();
        mock.setRating(5);
        mock.setUpdatedBy("Majken");
        mock.setCreatedBy("Jens");
        mock.setDescription("Outstanding");
        ReflectionTestUtils.setField(mock, "id", UUID.fromString("7c89ec02-63b9-4d68-9720-c22396fca1c7"));
        ReflectionTestUtils.setField(mock, "updated", OffsetDateTimeProvider.provide("2023-01-25T14:25:15"));
        ReflectionTestUtils.setField(mock, "created", OffsetDateTimeProvider.provide("2022-11-05T19:47:29"));
        return mock;
    }

    private static RatingDTO mockRatingDTO5() {
        RatingDTO dto = RatingDTO.builder()
                .id(UUID.fromString("7c89ec02-63b9-4d68-9720-c22396fca1c7"))
                .rating(5)
                .description("Outstanding")
                .createdBy("Majken")
                .updatedBy("Jens")
                .updated(OffsetDateTimeProvider.provide("2023-01-25T14:25:15"))
                .created(OffsetDateTimeProvider.provide("2022-11-05T19:47:29"))
                .build();
        return dto;
    }
}
