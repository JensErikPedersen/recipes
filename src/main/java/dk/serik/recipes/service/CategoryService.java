package dk.serik.recipes.service;

import java.util.List;
import java.util.Optional;
import java.util.function.IntPredicate;

import dk.serik.recipes.entity.CategoryEntity;

public interface CategoryService {

	Optional<List<CategoryEntity>> findAll();

	Optional<CategoryEntity> findById(String string);

	Optional<CategoryEntity> findByName(String string);


}
