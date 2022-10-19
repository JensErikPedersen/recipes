package dk.serik.recipes.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import dk.serik.recipes.entity.UnitEntity;

@Repository
public interface UnitJpaRepository extends JpaRepository<UnitEntity, UUID> {
	Optional<UnitEntity> findByDescriptionContains(String description);
	
	Optional<List<UnitEntity>> findAllByDescriptionContains(String description);
}
