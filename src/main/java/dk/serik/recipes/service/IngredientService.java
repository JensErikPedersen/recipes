package dk.serik.recipes.service;

import dk.serik.recipes.dto.IngredientDTO;

import java.util.List;
import java.util.Optional;

public interface IngredientService {

    Optional<List<IngredientDTO>> findAll();

    Optional<IngredientDTO> findById(String string);

    Optional<IngredientDTO> findByName(String string);

    Optional<List<IngredientDTO>> findAllByNameContains(String name);

    IngredientDTO save(IngredientDTO dto);

    void delete(String id);

    IngredientDTO update(IngredientDTO dto);

}
