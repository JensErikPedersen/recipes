package dk.serik.recipes.repository;

import dk.serik.recipes.model.RecipeRating;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RecipeRatingJpaRepository extends JpaRepository<RecipeRating, String> {
    Optional<List<RecipeRating>> findAllByRecipeIdAndRatingId(String recipeId, String ratingId);

    Optional<List<RecipeRating>> findAllByRecipeId(String recipeId);

    Optional<List<RecipeRating>> findAllByRatingId(String ratingId);

//    Optional<RecipeRating> findById(String ratingId);
}
