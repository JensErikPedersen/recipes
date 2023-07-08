package dk.serik.recipes.service;

import dk.serik.recipes.bean.Session;
import dk.serik.recipes.dto.IngredientDTO;
import dk.serik.recipes.mapper.IngredientMapper;
import dk.serik.recipes.model.Ingredient;
import dk.serik.recipes.repository.IngredientJpaRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

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
    private Session session;
    @Override
    public Optional<List<IngredientDTO>> findAll() {
        List<Ingredient> ingredients = repository.findAll();
        if(!ingredients.isEmpty()) {
            List<IngredientDTO> dtos =  ingredients.stream()
                    .map(i -> {
                        return IngredientMapper.from(i);
                    })
                    .collect(Collectors.toList());
            return Optional.of(dtos);
        }
        return Optional.empty();
    }
    @Override
    public Optional<IngredientDTO> findById(String id) {
        Optional<Ingredient> ingredient = repository.findById(UUID.fromString(id));
        if(ingredient.isPresent()) {
            return Optional.of(IngredientMapper.from(ingredient.get()));
        }
        return Optional.empty();
    }

    @Override
    public Optional<IngredientDTO> findByName(String name) {
        Optional<Ingredient> ingredient = repository.findByName(name);

        if(ingredient.isPresent()) {
            return Optional.of(IngredientMapper.from(ingredient.get()));
        }

        return Optional.empty();
    }

    @Override
    public Optional<List<IngredientDTO>> findAllByNameContains(String name) {
        Optional<List<Ingredient>> ingredients = repository.findAllByNameContains(name);

        if(ingredients.isPresent()) {
            List<IngredientDTO> dtos =  ingredients.get().stream()
                    .map(i -> {
                        return IngredientMapper.from(i);
                    })
                    .collect(Collectors.toList());
            return Optional.of(dtos);
        }

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
