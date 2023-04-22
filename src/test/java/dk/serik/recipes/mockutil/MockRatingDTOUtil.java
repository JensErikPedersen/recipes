package dk.serik.recipes.mockutil;

import dk.serik.recipes.dto.RatingDTO;
import dk.serik.recipes.testutil.OffsetDateTimeProvider;

public class MockRatingDTOUtil {

    public static RatingDTO mockRatingDTO5() {
        RatingDTO dto = RatingDTO.builder()
                .id("56789_rating5")
                .rating(5)
                .description("Outstanding")
                .createdBy("Majken")
                .updatedBy("Jens")
                .updated(OffsetDateTimeProvider.provide("2023-01-25T14:25:15"))
                .created(OffsetDateTimeProvider.provide("2022-11-05T19:47:29"))
                .build();
        return dto;
    }

    public static RatingDTO mockRatingDTO4() {
        RatingDTO dto = RatingDTO.builder()
                .id("56789_rating4")
                .rating(4)
                .description("Really Good")
                .createdBy("Majken")
                .updatedBy("Jens")
                .updated(OffsetDateTimeProvider.provide("2023-01-25T14:25:15"))
                .created(OffsetDateTimeProvider.provide("2022-11-05T19:47:29"))
                .build();
        return dto;
    }
    public static RatingDTO mockRatingDTO3() {
        RatingDTO dto = RatingDTO.builder()
                .id("56789_rating3")
                .rating(3)
                .description("Good")
                .createdBy("Majken")
                .updatedBy("Jens")
                .updated(OffsetDateTimeProvider.provide("2023-01-25T14:25:15"))
                .created(OffsetDateTimeProvider.provide("2022-11-05T19:47:29"))
                .build();
        return dto;
    }

    public static RatingDTO mockRatingDTO2() {
        RatingDTO dto = RatingDTO.builder()
                .id("56789_rating2")
                .rating(2)
                .description("Mediocre")
                .createdBy("Majken")
                .updatedBy("Jens")
                .updated(OffsetDateTimeProvider.provide("2023-01-25T14:25:15"))
                .created(OffsetDateTimeProvider.provide("2022-11-05T19:47:29"))
                .build();
        return dto;
    }

    public static RatingDTO mockRatingDTO1() {
        RatingDTO dto = RatingDTO.builder()
                .id("56789_rating1")
                .rating(1)
                .description("Well...")
                .createdBy("Majken")
                .updatedBy("Jens")
                .updated(OffsetDateTimeProvider.provide("2023-01-25T14:25:15"))
                .created(OffsetDateTimeProvider.provide("2022-11-05T19:47:29"))
                .build();
        return dto;
    }

}
