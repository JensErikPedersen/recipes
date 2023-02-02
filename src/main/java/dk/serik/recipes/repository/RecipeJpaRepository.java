package dk.serik.recipes.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import dk.serik.recipes.model.RecipeEntity;

@Repository
public interface RecipeJpaRepository extends JpaRepository<RecipeEntity, String> {
	
	List<RecipeEntity> findAllByCategoryEntityName(String categoryName);
	
	Optional<List<RecipeEntity>> findAllByNameContains(String name);
	
	Optional<RecipeEntity> findByName(String name);

}
