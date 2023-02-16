package dk.serik.recipes.repository;

import dk.serik.recipes.model.Rating;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RatingJpaRepository extends JpaRepository<Rating, String> {

}
