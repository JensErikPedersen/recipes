package dk.serik.recipes.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import dk.serik.recipes.model.RatingEntity;

@Repository
public interface RatingJpaRepository extends JpaRepository<RatingEntity, String> {

}
