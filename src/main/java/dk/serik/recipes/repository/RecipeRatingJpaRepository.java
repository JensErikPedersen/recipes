package dk.serik.recipes.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import dk.serik.recipes.entity.RecipeRatingEntity;

@Repository
public interface RecipeRatingJpaRepository extends JpaRepository<RecipeRatingEntity, String> {

}
