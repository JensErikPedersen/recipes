package dk.serik.recipes.repository;

import dk.serik.recipes.model.Ingredient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IngredientJpaRepository extends JpaRepository<Ingredient, String> {
	
	Optional<List<Ingredient>> findAllByNameContains(String name);
	
	Optional<List<Ingredient>> findAllByDescriptionContains(String name);
	
	Optional<Ingredient> findByName(String name);

}
