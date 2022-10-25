package dk.serik.recipes.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.within;

import java.time.OffsetDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;
import java.util.Set;

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

import dk.serik.recipes.Session;
import dk.serik.recipes.entity.CategoryEntity;
import dk.serik.recipes.entity.RecipeEntity;
import dk.serik.recipes.entity.RecipeIngredientEntity;
import lombok.extern.slf4j.Slf4j;

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
		List<RecipeEntity> recipes = recipeJpaRepository.findAll();
		Assertions.assertFalse(recipes.isEmpty());
		Assertions.assertEquals(7, recipes.size());
	}
	
	@Test
	@Sql("/db/test-data/insert_recipes.sql")
	public void givenExistingRecipe_WhenFetchingByKnownUUID_ThenOk() {
		Optional<RecipeEntity> reOptional = recipeJpaRepository.findById("4ea753a3-07ff-47ce-82bb-9dcc1aa25477");
		Assertions.assertTrue(reOptional.isPresent());
		Assertions.assertEquals("Stegt flæsk med persillesovs og kogte kartofler", reOptional.get().getName());
		Assertions.assertEquals("En klassisk menu til herremiddagen", reOptional.get().getDescription());
	}
	
	@Test
	@Sql("/db/test-data/insert_recipes.sql")
	public void givenCategoryNameIsHovedret_WhenFetchingRecipes_ThenThreeIsReturned() {
		List<RecipeEntity> recipes = recipeJpaRepository.findAllByCategoryEntityName("Hovedret");
		Assertions.assertFalse(recipes.isEmpty());
		Assertions.assertEquals(3, recipes.size());
	}
	
	@Test
	@Sql("/db/test-data/insert_recipes.sql")
	public void givenValidRecipe_WhenSavedToDatabase_ThenOk() {
		OffsetDateTime now = OffsetDateTime.now();
		RecipeEntity recipeEntity = recipeJpaRepository.saveAndFlush(buildRecipeEntity());
		Assertions.assertEquals("Gulerodskage", recipeEntity.getName());
		assertThat("Majken").isEqualTo( recipeEntity.getCreatedBy());
		assertThat(now).isCloseTo(recipeEntity.getCreated(), within(0, ChronoUnit.SECONDS));
		
//		Assertions.assertEquals("Majken", recipeEntity.getCreatedBy());
		List<RecipeEntity> recipes = recipeJpaRepository.findAllByCategoryEntityName("Kager");
		Assertions.assertFalse(recipes.isEmpty());
		Assertions.assertEquals(2, recipes.size());		
	}
		
	
	@Test
	@Sql("/db/test-data/insert_recipes.sql")
	public void givenExistingRecipe_WhenDeleted_ThenOk() {
		Optional<RecipeEntity> reOptional = recipeJpaRepository.findById("4ea753a3-07ff-47ce-82bb-9dcc1aa25477");
		Assertions.assertTrue(reOptional.isPresent());
		recipeJpaRepository.delete(reOptional.get());
		reOptional = recipeJpaRepository.findById("4ea753a3-07ff-47ce-82bb-9dcc1aa25477");
		Assertions.assertFalse(reOptional.isPresent());
		List<RecipeEntity> recipes = recipeJpaRepository.findAll();
		Assertions.assertFalse(recipes.isEmpty());
		Assertions.assertEquals(6, recipes.size());
	}
	
	@Test
	@Sql("/db/test-data/insert_recipes.sql")
	public void givenExistingRecipe_WhenNameIsChanged_ThenOk() {
		Optional<RecipeEntity> reOptional = recipeJpaRepository.findById("195f5356-2230-4122-9a23-a266151f865c");
		Assertions.assertTrue(reOptional.isPresent());		
		reOptional.get().setName("Kylling og bacon rykker!");
		OffsetDateTime now = OffsetDateTime.now();
		RecipeEntity saveAndFlush = recipeJpaRepository.saveAndFlush(reOptional.get());
		Assertions.assertEquals("Kylling og bacon rykker!", saveAndFlush.getName());
		assertThat(now).isCloseTo(saveAndFlush.getUpdated(), within(0, ChronoUnit.SECONDS));
		assertThat("Majken").isEqualTo(saveAndFlush.getUpdatedBy());
	}
	
	@Test
	@Sql("/db/test-data/insert_recipes.sql")
	public void givenTwoBreadRecipes_WhenFetchingRecipesNyNameBread_TheReturnTwo() {
		Optional<List<RecipeEntity>> opRecipes = recipeJpaRepository.findAllByNameContains("brød");
		Assertions.assertTrue(opRecipes.isPresent());
		Assertions.assertEquals(3, opRecipes.get().size());		
	}
	
	@Test
	@Sql({"/db/test-data/insert_recipes.sql", "/db/test-data/insert_recipe_ingredients.sql"})
	public void givenRecipeWithIngredients_WhenFetchingRecipe_ThenAllIngredientOk() {
		Optional<RecipeEntity> reOptional = recipeJpaRepository.findById("ce07075c-38b4-4b52-831c-5a9ce105e4af");  // Hvedebrød med Rugmel
		Assertions.assertTrue(reOptional.isPresent());
		Set<RecipeIngredientEntity> recipeIngredients = reOptional.get().getRecipeIngredientEntities();
		Assertions.assertTrue(!recipeIngredients.isEmpty());
		Assertions.assertEquals(6, recipeIngredients.size());
	}
	
	
	@Test
	@Sql({"/db/test-data/insert_recipes.sql", "/db/test-data/insert_recipe_ingredients.sql"})
	public void givenExistingRecipeWithIngredients_WhenDeleted_ThenOk() {
		Optional<RecipeEntity> reOptional = recipeJpaRepository.findById("5d22c394-b5ce-48c3-8199-72ccc92c737c");  // Fuldkorns hvedebrød
		Assertions.assertTrue(reOptional.isPresent());
		recipeJpaRepository.delete(reOptional.get());
		reOptional = recipeJpaRepository.findById("5d22c394-b5ce-48c3-8199-72ccc92c737c");
		Assertions.assertTrue(reOptional.isEmpty());
		
		// check for clean-up of recipeingrediententities
		Optional<List<RecipeIngredientEntity>> reIngOpList = recipeIngredientJpaRepository.findAllByRecipeEntityId("5d22c394-b5ce-48c3-8199-72ccc92c737c");
//		log.info("Recipe Ingredients size: {}", reIngOpList.get().size());
		Assertions.assertTrue(reIngOpList.isPresent());
		Assertions.assertEquals(0,  reIngOpList.get().size());
		
		List<RecipeEntity> recipes = recipeJpaRepository.findAll();
		Assertions.assertFalse(recipes.isEmpty());
		Assertions.assertEquals(6, recipes.size());
	}
	
	
	private RecipeEntity buildRecipeEntity() {
		RecipeEntity recipeEntity = new RecipeEntity();
		recipeEntity.setCategoryEntity(buildCategoryEntityKage());
		recipeEntity.setName("Gulerodskage");
		recipeEntity.setDescription("Lækker kage med lidt sundt indhold og creme på toppen");
		recipeEntity.setInstructions("1. Hæld mælk op i røreskålen...");		
		return recipeEntity;
	}
	
	private CategoryEntity buildCategoryEntityKage() {
		Optional<CategoryEntity> categoryKage = categoryRepository.findByName("Kager");
		return categoryKage.get();
	} 
}
