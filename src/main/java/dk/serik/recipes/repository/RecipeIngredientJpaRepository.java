package dk.serik.recipes.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import dk.serik.recipes.entity.IngredientEntity;
import dk.serik.recipes.entity.RecipeEntity;
import dk.serik.recipes.entity.RecipeIngredientEntity;

@Repository
public interface RecipeIngredientJpaRepository extends JpaRepository<RecipeIngredientEntity, String> {

	Optional<RecipeIngredientEntity> findByRecipeIdAndIngredientId(String recipeId, String ingredientId);
}
