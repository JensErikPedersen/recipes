package dk.serik.recipes.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import dk.serik.recipes.entity.RecipeEntity;

@Repository
public interface RecipeJpaRepository extends JpaRepository<RecipeEntity, UUID> {
	
	List<RecipeEntity> findAllByCategoryEntityName(String categoryName);
	
	Optional<List<RecipeEntity>> findAllByNameContains(String name);

}
