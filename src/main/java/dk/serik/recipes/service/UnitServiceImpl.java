package dk.serik.recipes.service;

import dk.serik.recipes.dto.UnitDTO;
import dk.serik.recipes.mapper.UnitMapper;
import dk.serik.recipes.model.Unit;
import dk.serik.recipes.repository.UnitJpaRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class UnitServiceImpl implements UnitService {

    private UnitJpaRepository repository;
    @Override
    public Optional<UnitDTO> findById(String id) {
        Optional<Unit> unit = repository.findById(UUID.fromString(id));
        if(unit.isPresent()) {
            return Optional.of(UnitMapper.from(unit.get()));
        }
        return Optional.empty();
    }

    @Override
    public Optional<List<UnitDTO>> findAll() {
        return Optional.empty();
    }

    @Override
    public UnitDTO save(UnitDTO dto) {
        return null;
    }

    @Override
    public boolean delete(String id) {
        return false;
    }

    @Override
    public UnitDTO update(UnitDTO dto) {
        return null;
    }
}
