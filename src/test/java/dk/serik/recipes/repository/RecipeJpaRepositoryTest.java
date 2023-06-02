package dk.serik.recipes.repository;

import dk.serik.recipes.bean.Session;
import dk.serik.recipes.model.Category;
import dk.serik.recipes.model.Recipe;
import dk.serik.recipes.model.RecipeIngredient;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.OffsetDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.within;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@Slf4j
public class RecipeJpaRepositoryTest {

	@Autowired
	private RecipeJpaRepository recipeJpaRepository;
	
	@Autowired
	private CategoryJpaRepository categoryRepository;
	
	@Autowired
	private RecipeIngredientJpaRepository recipeIngredientJpaRepository;
	
	@MockBean
	private Session session;
	
	@BeforeEach
	public void setupTest() {
		Mockito.when(session.getUserName()).thenReturn("Majken");
	}
	
	@Test
	@Sql("/db/test-data/insert_recipes.sql")
	@DisplayName("Given six existing Recipes, When fetching all, Then six is returned")
	public void givenExistingRecipes_WhenFetchingAll_ThenSixIsReturned() {
		//When
		List<Recipe> recipes = recipeJpaRepository.findAll();

		//Then
		Assertions.assertFalse(recipes.isEmpty());
		Assertions.assertEquals(7, recipes.size());
	}
	
	@Test
	@Sql("/db/test-data/insert_recipes.sql")
	@DisplayName("Given existing Recipe with known id, When fetching Recipe by id, Then recipe is fetched ok")
	public void givenExistingRecipe_WhenFetchingByKnownUUID_ThenOk() {
		//When
		Optional<Recipe> reOptional = recipeJpaRepository.findById("4ea753a3-07ff-47ce-82bb-9dcc1aa25477");

		//Then
		Assertions.assertTrue(reOptional.isPresent());
		Assertions.assertEquals("Stegt flæsk med persillesovs og kogte kartofler", reOptional.get().getName());
		Assertions.assertEquals("En klassisk menu til herremiddagen", reOptional.get().getDescription());
	}
	
	@Test
	@Sql("/db/test-data/insert_recipes.sql")
	@DisplayName("Given three recipes with categpry 'Hovedret', When all Recipes by category 'Hovedret' is fetched, Then three is returned")
	public void givenCategoryNameIsHovedret_WhenFetchingRecipes_ThenThreeIsReturned() {
		//When
		List<Recipe> recipes = recipeJpaRepository.findAllByCategoryName("Hovedret");

		//Then
		Assertions.assertFalse(recipes.isEmpty());
		Assertions.assertEquals(3, recipes.size());
	}
	
	@Test
	@Sql("/db/test-data/insert_recipes.sql")
	@DisplayName("Given new valid Recipe with category 'kager', When saved to database, Then Recipe is saved ok and one more recipe added to category 'Kager'")
	public void givenValidRecipe_WhenSavedToDatabase_ThenOk() {
		//When
		OffsetDateTime now = OffsetDateTime.now();
		Recipe recipe = recipeJpaRepository.saveAndFlush(buildRecipeEntity());

		// Then
		Assertions.assertEquals("Gulerodskage", recipe.getName());
		assertThat("Majken").isEqualTo( recipe.getCreatedBy());
		assertThat(now).isCloseTo(recipe.getCreated(), within(0, ChronoUnit.SECONDS));
		
//		Assertions.assertEquals("Majken", recipeEntity.getCreatedBy());
		List<Recipe> recipes = recipeJpaRepository.findAllByCategoryName("Kager");
		Assertions.assertFalse(recipes.isEmpty());
		Assertions.assertEquals(2, recipes.size());		
	}
		
	
	@Test
	@Sql("/db/test-data/insert_recipes.sql")
	@DisplayName("Given existing Recipe, When deleting Recipe, Then Recipe is removed")
	public void givenExistingRecipe_WhenDeleted_ThenOk() {
		// Given
		Optional<Recipe> reOptional = recipeJpaRepository.findById("4ea753a3-07ff-47ce-82bb-9dcc1aa25477");
		Assertions.assertTrue(reOptional.isPresent());

		//When
		recipeJpaRepository.delete(reOptional.get());

		//Then
		reOptional = recipeJpaRepository.findById("4ea753a3-07ff-47ce-82bb-9dcc1aa25477");
		Assertions.assertFalse(reOptional.isPresent());
		List<Recipe> recipes = recipeJpaRepository.findAll();
		Assertions.assertFalse(recipes.isEmpty());
		Assertions.assertEquals(6, recipes.size());
	}
	
	@Test
	@Sql("/db/test-data/insert_recipes.sql")
	@DisplayName("Given Recipe exist, When changing Name, Then all updated and updatedBy is changed")
	public void givenExistingRecipe_WhenNameIsChanged_ThenOk() {
		// Given
		Optional<Recipe> reOptional = recipeJpaRepository.findById("195f5356-2230-4122-9a23-a266151f865c");
		Assertions.assertTrue(reOptional.isPresent());

		// When
		reOptional.get().setName("Kylling og bacon rykker!");
		OffsetDateTime now = OffsetDateTime.now();
		Recipe saveAndFlush = recipeJpaRepository.saveAndFlush(reOptional.get());

		// Then
		Assertions.assertEquals("Kylling og bacon rykker!", saveAndFlush.getName());
		assertThat(now).isCloseTo(saveAndFlush.getUpdated(), within(0, ChronoUnit.SECONDS));
		assertThat("Majken").isEqualTo(saveAndFlush.getUpdatedBy());
	}
	
	@Test
	@Sql("/db/test-data/insert_recipes.sql")
	@DisplayName("Given Two Breads exists, When Fetching All By name contains 'brød', Then two are fetched")
	public void givenTwoBreadRecipes_WhenFetchingRecipesNyNameBread_TheReturnTwo() {
		// When
		Optional<List<Recipe>> opRecipes = recipeJpaRepository.findAllByNameContains("brød");

		// Then
		Assertions.assertTrue(opRecipes.isPresent());
		Assertions.assertEquals(3, opRecipes.get().size());		
	}
	
	@Test
	@Sql({"/db/test-data/insert_recipes.sql", "/db/test-data/insert_recipe_ingredients.sql"})
	@DisplayName("Given Recipe with 6 Ingredients, When Recipe is Fetched, Then Recipe and RecipeIngredient are fetched")
	public void givenRecipeWithIngredients_WhenFetchingRecipe_ThenAllIngredientOk() {
		// When
		Optional<Recipe> reOptional = recipeJpaRepository.findById("ce07075c-38b4-4b52-831c-5a9ce105e4af");  // Hvedebrød med Rugmel
		Assertions.assertTrue(reOptional.isPresent());
		log.info("Recipe: {}", reOptional.get());
		Set<RecipeIngredient> recipeIngredients = reOptional.get().getRecipeIngredients();

		// Then
		Assertions.assertTrue(!recipeIngredients.isEmpty());
		Assertions.assertEquals(6, recipeIngredients.size());
	}
	
	
	@Test
	@Sql({"/db/test-data/insert_recipes.sql", "/db/test-data/insert_recipe_ingredients.sql"})
	@DisplayName("Given Recipe with Ingredient, When Recipe is Deleted, Then Recipe and All RecipeIngredients are deleted")
	public void givenExistingRecipeWithIngredients_WhenDeleted_ThenOk() {
		// Given
		Optional<Recipe> reOptional = recipeJpaRepository.findById("5d22c394-b5ce-48c3-8199-72ccc92c737c");  // Fuldkorns hvedebrød
		Assertions.assertTrue(reOptional.isPresent());

		// When
		recipeJpaRepository.delete(reOptional.get());
		reOptional = recipeJpaRepository.findById("5d22c394-b5ce-48c3-8199-72ccc92c737c");

		// Then
		Assertions.assertTrue(reOptional.isEmpty());
		
		// check for clean-up of recipeingrediententities
		Optional<List<RecipeIngredient>> reIngOpList = recipeIngredientJpaRepository.findAllByRecipeId("5d22c394-b5ce-48c3-8199-72ccc92c737c");
//		log.info("Recipe Ingredients size: {}", reIngOpList.get().size());
		Assertions.assertTrue(reIngOpList.isPresent());
		Assertions.assertEquals(0,  reIngOpList.get().size());
		
		List<Recipe> recipes = recipeJpaRepository.findAll();
		Assertions.assertFalse(recipes.isEmpty());
		Assertions.assertEquals(6, recipes.size());
	}


	
	private Recipe buildRecipeEntity() {
		Recipe recipe = new Recipe();
		recipe.setCategory(buildCategoryEntityKage());
		recipe.setName("Gulerodskage");
		recipe.setDescription("Lækker kage med lidt sundt indhold og creme på toppen");
		recipe.setInstructions("1. Hæld mælk op i røreskålen...");
		return recipe;
	}
	
	private Category buildCategoryEntityKage() {
		Optional<Category> categoryKage = categoryRepository.findByName("Kager");
		return categoryKage.get();
	} 
}
