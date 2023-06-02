package dk.serik.recipes.mapper;

import dk.serik.recipes.dto.TagDTO;
import dk.serik.recipes.model.Tag;
import lombok.extern.slf4j.Slf4j;

import java.util.Objects;

@Slf4j
public class TagMapper {
    public static TagDTO from(Tag entity) {
        if(Objects.isNull(entity)) {
            return null;
        }
        TagDTO dto = TagDTO.builder()
                .id(entity.getId())
                .label(entity.getLabel())
                .createdBy(entity.getCreatedBy())
                .created(entity.getCreated())
                .updatedBy(entity.getUpdatedBy())
                .updatedBy(entity.getUpdatedBy())
                .build();
        log.info("Mapped DTO: {}", dto);
        return dto;
    }
}
