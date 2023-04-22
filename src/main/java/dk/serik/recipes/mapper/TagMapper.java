package dk.serik.recipes.mapper;

import dk.serik.recipes.dto.TagDTO;
import dk.serik.recipes.model.Tag;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper
public interface TagMapper {
    Tag tagDTOToTag(TagDTO tagDTO);
    @Mapping(target = "recipes", ignore = true)
    TagDTO tagToTagDTO(Tag tag);
}
