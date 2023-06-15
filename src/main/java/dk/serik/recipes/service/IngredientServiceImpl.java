package dk.serik.recipes.service;

import dk.serik.recipes.dto.IngredientDTO;
import dk.serik.recipes.repository.IngredientJpaRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@Transactional(
        isolation = Isolation.READ_COMMITTED,
        propagation = Propagation.REQUIRED,
        readOnly = false,
        timeout = 5)
@Slf4j
public class IngredientServiceImpl implements IngredientService {

    private IngredientJpaRepository repository;
    @Override
    public Optional<List<IngredientDTO>> findAll() {
        return Optional.empty();
    }
    @Override
    public Optional<IngredientDTO> findById(String string) {
        return Optional.empty();
    }

    @Override
    public Optional<IngredientDTO> findByName(String string) {
        return Optional.empty();
    }

    @Override
    public Optional<List<IngredientDTO>> findAllByNameContains(String name) {
        return Optional.empty();
    }

    @Override
    public Optional<List<IngredientDTO>> findAllByDescriptionContains(String name) {
        return Optional.empty();
    }

    @Override
    public IngredientDTO save(IngredientDTO dto) {
        return null;
    }

    @Override
    public void delete(String id) {

    }

    @Override
    public IngredientDTO update(IngredientDTO dto) {
        return null;
    }
}
