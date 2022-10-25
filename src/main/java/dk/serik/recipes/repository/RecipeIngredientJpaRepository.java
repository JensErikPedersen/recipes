package dk.serik.recipes.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import dk.serik.recipes.entity.RecipeIngredientEntity;

@Repository
public interface RecipeIngredientJpaRepository extends JpaRepository<RecipeIngredientEntity, String> {

	Optional<RecipeIngredientEntity> findByRecipeEntityIdAndIngredientEntityId(String recipeId, String ingredientId);
	
	Optional<List<RecipeIngredientEntity>> findAllByRecipeEntityId(String recipeId);
	
	Optional<List<RecipeIngredientEntity>> findAllByIngredientEntityId(String ingredientId);
}
