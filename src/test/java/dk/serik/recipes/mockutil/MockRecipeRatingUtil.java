package dk.serik.recipes.mockutil;

import dk.serik.recipes.model.RecipeRating;
import dk.serik.recipes.testutil.OffsetDateTimeProvider;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.HashSet;
import java.util.Set;

public class MockRecipeRatingUtil {

    public static RecipeRating mockRecipeHvedebreadRating1() {
        RecipeRating mock = new RecipeRating();
        mock.setRecipe(MockRecipeUtil.mockRecipeHvedebread());
        mock.setRating(MockRatingUtil.mockRating4());
        mock.setDescription("Den er i skabet");
        ReflectionTestUtils.setField(mock, "id", "34567_hvedebroed_rating1");
        ReflectionTestUtils.setField(mock, "created", OffsetDateTimeProvider.provide("2022-11-05T19:47:29"));
        mock.setCreatedBy("Majken");
        return mock;
    }

    public static RecipeRating mockRecipeHvedebreadRating2() {
        RecipeRating mock = new RecipeRating();
        mock.setRecipe(MockRecipeUtil.mockRecipeHvedebread());
        mock.setRating(MockRatingUtil.mockRating5());
        mock.setDescription("Den er perfekt");
        mock.setCreatedBy("Jens");
        ReflectionTestUtils.setField(mock, "id", "34567_hvedebroed_rating2");
        ReflectionTestUtils.setField(mock, "created", OffsetDateTimeProvider.provide("2022-11-05T19:47:29"));
        return mock;
    }

    public static RecipeRating mockRecipeHvedebreadRating3() {
        RecipeRating mock = new RecipeRating();
        mock.setRecipe(MockRecipeUtil.mockRecipeHvedebread());
        mock.setRating(MockRatingUtil.mockRating5());
        mock.setDescription("Skønt");
        mock.setCreatedBy("Oskar");
        ReflectionTestUtils.setField(mock, "id", "34567_hvedebroed_rating3");
        ReflectionTestUtils.setField(mock, "created", OffsetDateTimeProvider.provide("2022-11-05T19:47:29"));
        return mock;
    }

    public static Set<RecipeRating> mockRecipeRatings() {
        Set<RecipeRating> mock = new HashSet<>();
        mock.add(mockRecipeHvedebreadRating1());
        mock.add(mockRecipeHvedebreadRating2());
        mock.add(mockRecipeHvedebreadRating3());
        return mock;
    }


}
