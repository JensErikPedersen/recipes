package dk.serik.recipes.mockutil;

import dk.serik.recipes.model.Recipe;
import dk.serik.recipes.testutil.OffsetDateTimeProvider;
import org.springframework.test.util.ReflectionTestUtils;

public class MockRecipeUtil {

    public static Recipe mockRecipeHvedebread() {
        Recipe mock = new Recipe();
        mock.setCategory(MockCategoryUtil.mockBread());
        mock.setName("Hvedebrød");
        mock.setDescription("Lækkert brød til morgenmad eller til kaffen med ost på");
        mock.setInstructions("1. Hæld vand og gær i kedel. 2. Rør..");
        mock.setRecipeIngredients(MockRecipeIngredientUtil.mockRecipeIngredientsHvedebread());
        mock.setRecipeRatings(MockRecipeRatingUtil.mockRecipeRatings());
        mock.setCreatedBy("Majken");
        mock.setUpdatedBy("Jens");
        ReflectionTestUtils.setField(mock, "id", "12345_hvedebroed");
        ReflectionTestUtils.setField(mock, "updated", OffsetDateTimeProvider.provide("2023-01-25T14:25:15"));
        ReflectionTestUtils.setField(mock, "created", OffsetDateTimeProvider.provide("2022-11-05T19:47:29"));
        return mock;
    }



}
