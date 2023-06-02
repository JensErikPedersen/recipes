package dk.serik.recipes.repository;

import dk.serik.recipes.model.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TagJpaRepository extends JpaRepository<Tag,String> {
    Optional<Tag> findTagByLabel(String label);

}
