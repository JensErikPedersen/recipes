package dk.serik.recipes.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import dk.serik.recipes.entity.IngredientEntity;

@Repository
public interface IngredientJpaRepository extends JpaRepository<IngredientEntity, String> {
	
	Optional<List<IngredientEntity>> findAllByNameContains(String name);
	
	Optional<List<IngredientEntity>> findAllByDescriptionContains(String name);
	
	Optional<IngredientEntity> findByName(String name);

}
