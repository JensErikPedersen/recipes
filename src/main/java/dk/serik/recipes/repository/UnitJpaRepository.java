package dk.serik.recipes.repository;

import dk.serik.recipes.model.Unit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface UnitJpaRepository extends JpaRepository<Unit, UUID> {
	Optional<Unit> findByDescriptionContains(String description);
	
	Optional<List<Unit>> findAllByDescriptionContains(String description);
}
