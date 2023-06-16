package dk.serik.recipes.mapper;

import dk.serik.recipes.dto.UnitDTO;
import dk.serik.recipes.model.Unit;
import lombok.extern.slf4j.Slf4j;

import java.util.Objects;

@Slf4j
public class UnitMapper {

    public static UnitDTO from(Unit entity) {
        if(Objects.isNull(entity)) {
            return null;
        }

        UnitDTO dto = UnitDTO.builder()
                .id(entity.getId())
                .name(entity.getName())
                .createdBy(entity.getCreatedBy())
                .created(entity.getCreated())
                .updatedBy(entity.getUpdatedBy())
                .updated(entity.getUpdated())
                .description(entity.getDescription())
                .build();
        log.info("Mapped DTO: {}", dto);
        return dto;
    }
}
