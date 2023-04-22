package dk.serik.recipes.mockutil;

import dk.serik.recipes.model.RecipeIngredient;
import dk.serik.recipes.testutil.OffsetDateTimeProvider;
import org.springframework.test.util.ReflectionTestUtils;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

public class MockRecipeIngredientUtil {

    public static RecipeIngredient mockRecipeIngredientGaer() {
        RecipeIngredient mock = new RecipeIngredient();
        mock.setRecipe(MockRecipeUtil.mockRecipeHvedebread());
        mock.setIngredient(MockIngredientUtil.mockGaer());
        mock.setUnit(MockUnitUtil.mockGram());
        mock.setAmount(new BigDecimal(10));
        mock.setCreatedBy("Majken");
        mock.setUpdatedBy("Jens");
        ReflectionTestUtils.setField(mock, "id", "56789_gaer");
        ReflectionTestUtils.setField(mock, "updated", OffsetDateTimeProvider.provide("2023-03-25T14:25:15"));
        ReflectionTestUtils.setField(mock, "created", OffsetDateTimeProvider.provide("2022-12-05T19:47:29"));
        return mock;
    }

    public static RecipeIngredient mockRecipeIngredientWater() {
        RecipeIngredient mock = new RecipeIngredient();
        mock.setRecipe(MockRecipeUtil.mockRecipeHvedebread());
        mock.setIngredient(MockIngredientUtil.mockVand());
        mock.setUnit(MockUnitUtil.mockDl());
        mock.setAmount(new BigDecimal(5));
        mock.setCreatedBy("Majken");
        mock.setUpdatedBy("Jens");
        ReflectionTestUtils.setField(mock, "id", "56789_vand");
        ReflectionTestUtils.setField(mock, "updated", OffsetDateTimeProvider.provide("2023-03-25T14:25:15"));
        ReflectionTestUtils.setField(mock, "created", OffsetDateTimeProvider.provide("2022-12-05T19:47:29"));
        return mock;
    }

    public static RecipeIngredient mockRecipeIngredientSurdej() {
        RecipeIngredient mock = new RecipeIngredient();
        mock.setRecipe(MockRecipeUtil.mockRecipeHvedebread());
        mock.setIngredient(MockIngredientUtil.mockSurdej());
        mock.setUnit(MockUnitUtil.mockDl());
        mock.setAmount(new BigDecimal(1));
        mock.setCreatedBy("Majken");
        mock.setUpdatedBy("Jens");
        ReflectionTestUtils.setField(mock, "id", "56789_surdej");
        ReflectionTestUtils.setField(mock, "updated", OffsetDateTimeProvider.provide("2023-03-25T14:43:18"));
        ReflectionTestUtils.setField(mock, "created", OffsetDateTimeProvider.provide("2022-12-15T15:47:29"));
        return mock;
    }

    public static RecipeIngredient mockRecipeIngredientHvedemel() {
        RecipeIngredient mock = new RecipeIngredient();
        mock.setRecipe(MockRecipeUtil.mockRecipeHvedebread());
        mock.setIngredient(MockIngredientUtil.mockHvedemel());
        mock.setUnit(MockUnitUtil.mockGram());
        mock.setAmount(new BigDecimal(550));
        mock.setCreatedBy("Majken");
        mock.setUpdatedBy("Jens");
        ReflectionTestUtils.setField(mock, "id", "56789_hvedemel");
        ReflectionTestUtils.setField(mock, "updated", OffsetDateTimeProvider.provide("2023-03-12T14:43:18"));
        ReflectionTestUtils.setField(mock, "created", OffsetDateTimeProvider.provide("2022-12-15T15:47:29"));
        return mock;
    }

    public static RecipeIngredient mockRecipeIngredientSalt() {
        RecipeIngredient mock = new RecipeIngredient();
        mock.setRecipe(MockRecipeUtil.mockRecipeHvedebread());
        mock.setIngredient(MockIngredientUtil.mockSalt());
        mock.setUnit(MockUnitUtil.mockGram());
        mock.setAmount(new BigDecimal(16));
        mock.setCreatedBy("Majken");
        mock.setUpdatedBy("Jens");
        ReflectionTestUtils.setField(mock, "id", "56789_salt");
        ReflectionTestUtils.setField(mock, "updated", OffsetDateTimeProvider.provide("2023-03-06T14:43:18"));
        ReflectionTestUtils.setField(mock, "created", OffsetDateTimeProvider.provide("2022-12-29T15:47:29"));
        return mock;
    }

    public static RecipeIngredient mockRecipeIngredientRugmel() {
        RecipeIngredient mock = new RecipeIngredient();
        mock.setRecipe(MockRecipeUtil.mockRecipeHvedebread());
        mock.setIngredient(MockIngredientUtil.mockRugmel());
        mock.setUnit(MockUnitUtil.mockGram());
        mock.setAmount(new BigDecimal(170));
        ReflectionTestUtils.setField(mock, "id", "56789_rugmel");
        ReflectionTestUtils.setField(mock, "updated", OffsetDateTimeProvider.provide("2023-04-12T14:43:18"));
        ReflectionTestUtils.setField(mock, "created", OffsetDateTimeProvider.provide("2023-03-15T15:47:29"));
        return mock;
    }

    public static Set<RecipeIngredient> mockRecipeIngredientsHvedebread() {
        Set<RecipeIngredient> mock = new HashSet<>();
        mock.add(mockRecipeIngredientGaer());
        mock.add(mockRecipeIngredientSalt());
        mock.add(mockRecipeIngredientWater());
        mock.add(mockRecipeIngredientSurdej());
        mock.add(mockRecipeIngredientHvedemel());
        return mock;
    }


}
