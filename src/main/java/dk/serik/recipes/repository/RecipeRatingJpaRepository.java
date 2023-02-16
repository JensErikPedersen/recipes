package dk.serik.recipes.repository;

import dk.serik.recipes.model.RecipeRating;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecipeRatingJpaRepository extends JpaRepository<RecipeRating, String> {

}
