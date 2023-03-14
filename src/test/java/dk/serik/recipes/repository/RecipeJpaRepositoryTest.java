package dk.serik.recipes.repository;

import dk.serik.recipes.bean.Session;
import dk.serik.recipes.model.Category;
import dk.serik.recipes.model.Recipe;
import dk.serik.recipes.model.RecipeIngredient;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
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
	public void givenExistingRecipes_WhenFetchingAll_ThenSixIsReturned() {
		List<Recipe> recipes = recipeJpaRepository.findAll();
		Assertions.assertFalse(recipes.isEmpty());
		Assertions.assertEquals(7, recipes.size());
	}
	
	@Test
	@Sql("/db/test-data/insert_recipes.sql")
	public void givenExistingRecipe_WhenFetchingByKnownUUID_ThenOk() {
		Optional<Recipe> reOptional = recipeJpaRepository.findById("4ea753a3-07ff-47ce-82bb-9dcc1aa25477");
		Assertions.assertTrue(reOptional.isPresent());
		Assertions.assertEquals("Stegt flæsk med persillesovs og kogte kartofler", reOptional.get().getName());
		Assertions.assertEquals("En klassisk menu til herremiddagen", reOptional.get().getDescription());
	}
	
	@Test
	@Sql("/db/test-data/insert_recipes.sql")
	public void givenCategoryNameIsHovedret_WhenFetchingRecipes_ThenThreeIsReturned() {
		List<Recipe> recipes = recipeJpaRepository.findAllByCategoryName("Hovedret");
		Assertions.assertFalse(recipes.isEmpty());
		Assertions.assertEquals(3, recipes.size());
	}
	
	@Test
	@Sql("/db/test-data/insert_recipes.sql")
	public void givenValidRecipe_WhenSavedToDatabase_ThenOk() {
		OffsetDateTime now = OffsetDateTime.now();
		Recipe recipe = recipeJpaRepository.saveAndFlush(buildRecipeEntity());
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
	public void givenExistingRecipe_WhenDeleted_ThenOk() {
		Optional<Recipe> reOptional = recipeJpaRepository.findById("4ea753a3-07ff-47ce-82bb-9dcc1aa25477");
		Assertions.assertTrue(reOptional.isPresent());
		recipeJpaRepository.delete(reOptional.get());
		reOptional = recipeJpaRepository.findById("4ea753a3-07ff-47ce-82bb-9dcc1aa25477");
		Assertions.assertFalse(reOptional.isPresent());
		List<Recipe> recipes = recipeJpaRepository.findAll();
		Assertions.assertFalse(recipes.isEmpty());
		Assertions.assertEquals(6, recipes.size());
	}
	
	@Test
	@Sql("/db/test-data/insert_recipes.sql")
	public void givenExistingRecipe_WhenNameIsChanged_ThenOk() {
		Optional<Recipe> reOptional = recipeJpaRepository.findById("195f5356-2230-4122-9a23-a266151f865c");
		Assertions.assertTrue(reOptional.isPresent());		
		reOptional.get().setName("Kylling og bacon rykker!");
		OffsetDateTime now = OffsetDateTime.now();
		Recipe saveAndFlush = recipeJpaRepository.saveAndFlush(reOptional.get());
		Assertions.assertEquals("Kylling og bacon rykker!", saveAndFlush.getName());
		assertThat(now).isCloseTo(saveAndFlush.getUpdated(), within(0, ChronoUnit.SECONDS));
		assertThat("Majken").isEqualTo(saveAndFlush.getUpdatedBy());
	}
	
	@Test
	@Sql("/db/test-data/insert_recipes.sql")
	public void givenTwoBreadRecipes_WhenFetchingRecipesNyNameBread_TheReturnTwo() {
		Optional<List<Recipe>> opRecipes = recipeJpaRepository.findAllByNameContains("brød");
		Assertions.assertTrue(opRecipes.isPresent());
		Assertions.assertEquals(3, opRecipes.get().size());		
	}
	
	@Test
	@Sql({"/db/test-data/insert_recipes.sql", "/db/test-data/insert_recipe_ingredients.sql"})
	public void givenRecipeWithIngredients_WhenFetchingRecipe_ThenAllIngredientOk() {
		Optional<Recipe> reOptional = recipeJpaRepository.findById("ce07075c-38b4-4b52-831c-5a9ce105e4af");  // Hvedebrød med Rugmel
		Assertions.assertTrue(reOptional.isPresent());
		log.info("Recipe: {}", reOptional.get());
		Set<RecipeIngredient> recipeIngredients = reOptional.get().getRecipeIngredientEntities();
		Assertions.assertTrue(!recipeIngredients.isEmpty());
		Assertions.assertEquals(6, recipeIngredients.size());
	}
	
	
	@Test
	@Sql({"/db/test-data/insert_recipes.sql", "/db/test-data/insert_recipe_ingredients.sql"})
	public void givenExistingRecipeWithIngredients_WhenDeleted_ThenOk() {
		Optional<Recipe> reOptional = recipeJpaRepository.findById("5d22c394-b5ce-48c3-8199-72ccc92c737c");  // Fuldkorns hvedebrød
		Assertions.assertTrue(reOptional.isPresent());
		recipeJpaRepository.delete(reOptional.get());
		reOptional = recipeJpaRepository.findById("5d22c394-b5ce-48c3-8199-72ccc92c737c");
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
