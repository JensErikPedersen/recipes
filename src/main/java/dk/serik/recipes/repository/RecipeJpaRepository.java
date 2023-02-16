package dk.serik.recipes.repository;

import dk.serik.recipes.model.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RecipeJpaRepository extends JpaRepository<Recipe, String> {
	
	List<Recipe> findAllByCategoryName(String categoryName);
	
	Optional<List<Recipe>> findAllByNameContains(String name);
	
	Optional<Recipe> findByName(String name);

}
