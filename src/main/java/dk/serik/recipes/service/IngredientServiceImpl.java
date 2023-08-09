package dk.serik.recipes.service;

import dk.serik.recipes.bean.Session;
import dk.serik.recipes.dto.IngredientDTO;
import dk.serik.recipes.exceptions.ApplicationErrorCodes;
import dk.serik.recipes.exceptions.ServiceException;
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
import java.util.Objects;
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
        if(Objects.nonNull(dto)) {
            Ingredient entity = new Ingredient();
            entity.setDescription(dto.getDescription());
            entity.setName(dto.getName());
            entity.setCreatedBy(session.getUserName());
            Ingredient managedIngredient = repository.save(entity);
            return IngredientMapper.from(managedIngredient);
        }
        throw ServiceException.builder()
                .message("Ingredient is Null")
                .code(ApplicationErrorCodes.INGREDIENT_DTO_IS_NULL.getCode())
                .build();
    }

    @Override
    public boolean delete(String id) {
        if(Objects.isNull(id)) {
            throw ServiceException.builder()
                    .message("Ingredient Id is Null")
                    .code(ApplicationErrorCodes.INGREDIENT_ID_IS_NULL.getCode())
                    .build();
        }
        Optional<Ingredient> optional = repository.findById(UUID.fromString(id));
        if(optional.isPresent()) {
            repository.delete(optional.get());
            return true;
        }
        return false;
    }

    @Override
    public IngredientDTO update(IngredientDTO dto) {
        Optional<Ingredient> optionalIngredient = repository.findById(UUID.fromString(dto.getId()));
        if(optionalIngredient.isPresent()) {
           Ingredient managedIngredient = optionalIngredient.get();
           managedIngredient.setDescription(dto.getDescription());
           managedIngredient.setName(dto.getName());
           repository.save(managedIngredient);
           return IngredientMapper.from(managedIngredient);
        } else {
            throw ServiceException.builder()
                    .message("Could not update Ingredient with id" + dto.getId() + " since it was not found")
                    .code(ApplicationErrorCodes.INGREDIENT_NOT_FOUND.getCode())
                    .build();
        }
    }
}
