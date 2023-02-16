package dk.serik.recipes.repository;

import dk.serik.recipes.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CategoryJpaRepository extends JpaRepository<Category,String>{
	Optional<Category> findByName(String name);
	
	Optional<List<Category>> findAllByNameContains(String name);
	
	Optional<Category> findByNameContains(String name);

}
