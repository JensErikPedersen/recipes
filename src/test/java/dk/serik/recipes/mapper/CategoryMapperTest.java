package dk.serik.recipes.mapper;

import dk.serik.recipes.dto.CategoryDTO;
import dk.serik.recipes.model.Category;
import dk.serik.recipes.mockutil.MockCategoryDTOUtil;
import dk.serik.recipes.mockutil.MockCategoryUtil;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

public class CategoryMapperTest {

    private CategoryMapper mapper = Mappers.getMapper(CategoryMapper.class);
    @Test
    @DisplayName("Given valid entity, When mapped by mapper, Then DTO is Ok")
    public void passMapperFromValidEntityToDto() {
        CategoryDTO mappedDto = mapper.categoryToDTO(MockCategoryUtil.mockBread());
        Assertions.assertThat(mappedDto).isNotNull();
        Assertions.assertThat(mappedDto).isEqualTo(MockCategoryDTOUtil.mockCategoryDTO());
    }

    @Test
    @DisplayName("Given valid dto, When mapped by mapper, Then Entity is Ok")
    public void passMapperFromValidDtoToEntity() {
        Category mappedEntity = mapper.categoryDTOToCategory(MockCategoryDTOUtil.mockCategoryDTO());
        Assertions.assertThat(mappedEntity).isNotNull();
        Assertions.assertThat(mappedEntity.getName()).isEqualTo(MockCategoryUtil.mockBread().getName());
        Assertions.assertThat(mappedEntity.getDescription()).isEqualTo(MockCategoryUtil.mockBread().getDescription());
    }


    @Test
    @DisplayName("Given Unit is null, When mapped to DTO, Then DTO is Null")
    public void passMapperFromNullEntityToNullDto() {
        CategoryDTO mappedDto = mapper.categoryToDTO(null);
        Assertions.assertThat(mappedDto).isNull();
    }

    @Test
    @DisplayName("Given UnitDTO is null, When mapped to Entity, Then Entity is Null")
    public void passMapperFromNullDtoToNullEntity() {
        Category mappedDto = mapper.categoryDTOToCategory(null);
        Assertions.assertThat(mappedDto).isNull();
    }


}
