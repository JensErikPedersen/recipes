package dk.serik.recipes.mockutil;

import dk.serik.recipes.model.Rating;
import dk.serik.recipes.testutil.OffsetDateTimeProvider;
import org.springframework.test.util.ReflectionTestUtils;

public class MockRatingUtil {
    public static Rating mockRating5() {
        Rating mock = new Rating();
        mock.setRating(5);
        mock.setUpdatedBy("Majken");
        mock.setCreatedBy("Jens");
        mock.setDescription("Outstanding");
        ReflectionTestUtils.setField(mock, "id", "56789_rating5");
        ReflectionTestUtils.setField(mock, "updated", OffsetDateTimeProvider.provide("2023-01-25T14:25:15"));
        ReflectionTestUtils.setField(mock, "created", OffsetDateTimeProvider.provide("2022-11-05T19:47:29"));
        return mock;
    }

    public static Rating mockRating4() {
        Rating mock = new Rating();
        mock.setRating(4);
        mock.setDescription("");
        mock.setUpdatedBy("Majken");
        mock.setCreatedBy("Jens");
        mock.setDescription("Really Good");
        ReflectionTestUtils.setField(mock, "id", "56789_rating4");
        ReflectionTestUtils.setField(mock, "updated", OffsetDateTimeProvider.provide("2023-01-25T14:25:15"));
        ReflectionTestUtils.setField(mock, "created", OffsetDateTimeProvider.provide("2022-11-05T19:47:29"));
        return mock;
    }

    public static Rating mockRating3() {
        Rating mock = new Rating();
        mock.setRating(3);
        mock.setUpdatedBy("Jens");
        mock.setCreatedBy("Majken");
        mock.setDescription("Good");
        ReflectionTestUtils.setField(mock, "id", "56789_rating3");
        ReflectionTestUtils.setField(mock, "updated", OffsetDateTimeProvider.provide("2023-01-25T14:25:15"));
        ReflectionTestUtils.setField(mock, "created", OffsetDateTimeProvider.provide("2022-11-05T19:47:29"));
        return mock;
    }

    public static Rating mockRating2() {
        Rating mock = new Rating();
        mock.setRating(2);
        mock.setUpdatedBy("Jens");
        mock.setCreatedBy("Majken");
        mock.setDescription("Mediocre");
        ReflectionTestUtils.setField(mock, "id", "56789_rating2");
        ReflectionTestUtils.setField(mock, "updated", OffsetDateTimeProvider.provide("2023-01-25T14:25:15"));
        ReflectionTestUtils.setField(mock, "created", OffsetDateTimeProvider.provide("2022-11-05T19:47:29"));
        return mock;
    }

    public static Rating mockRating1() {
        Rating mock = new Rating();
        mock.setRating(1);
        mock.setUpdatedBy("Jens");
        mock.setCreatedBy("Majken");
        mock.setDescription("Well...");
        ReflectionTestUtils.setField(mock, "id", "56789_rating1");
        ReflectionTestUtils.setField(mock, "updated", OffsetDateTimeProvider.provide("2023-01-25T14:25:15"));
        ReflectionTestUtils.setField(mock, "created", OffsetDateTimeProvider.provide("2022-11-05T19:47:29"));
        return mock;
    }





}
