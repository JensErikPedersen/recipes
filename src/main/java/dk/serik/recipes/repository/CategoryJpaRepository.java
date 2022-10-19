package dk.serik.recipes.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import dk.serik.recipes.entity.CategoryEntity;

@Repository
public interface CategoryJpaRepository extends JpaRepository<CategoryEntity, UUID>{
	Optional<CategoryEntity> findByName(String name);
	
	Optional<List<CategoryEntity>> findAllByNameContains(String name);
	
	Optional<CategoryEntity> findByNameContains(String name);

}
