package dk.serik.recipes.mockutil;

import dk.serik.recipes.model.Category;
import dk.serik.recipes.testutil.OffsetDateTimeProvider;
import org.springframework.test.util.ReflectionTestUtils;

public class MockCategoryUtil {

    public static Category mockBread() {
        Category mock = new Category();
        mock.setName("Brød");
        mock.setDescription("Brød til alle måltider");
        mock.setUpdatedBy("Jens");
        mock.setCreatedBy("Majken");
        ReflectionTestUtils.setField(mock, "id", "12345_bread");
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
        ReflectionTestUtils.setField(mock, "id", "12345_salat");
        ReflectionTestUtils.setField(mock, "updated", OffsetDateTimeProvider.provide("2023-02-25T14:25:15"));
        ReflectionTestUtils.setField(mock, "created", OffsetDateTimeProvider.provide("2022-12-05T19:47:29"));
        return mock;
    }


}
