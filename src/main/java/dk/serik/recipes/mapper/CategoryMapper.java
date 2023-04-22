package dk.serik.recipes.mapper;

import dk.serik.recipes.dto.CategoryDTO;
import dk.serik.recipes.model.Category;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;


//@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
@Mapper
public interface CategoryMapper {

    @Mapping(target = "recipes", ignore = true)
    CategoryDTO categoryToDTO(Category category);
    Category categoryDTOToCategory(CategoryDTO categoryDTO);
}
