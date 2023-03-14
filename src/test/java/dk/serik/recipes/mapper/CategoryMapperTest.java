package dk.serik.recipes.mapper;

import dk.serik.recipes.dto.CategoryDTO;
import dk.serik.recipes.model.Category;
import dk.serik.recipes.testutil.OffsetDateTimeProvider;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import org.springframework.test.util.ReflectionTestUtils;

public class CategoryMapperTest {

    private CategoryMapper mapper = Mappers.getMapper(CategoryMapper.class);
    @Test
    @DisplayName("Given valid entity, When mapped by mapper, Then DTO is Ok")
    public void passMapperFromValidEntityToDto() {
        CategoryDTO mappedDto = mapper.category(mockCategory());
        Assertions.assertThat(mappedDto).isNotNull();
        Assertions.assertThat(mappedDto).isEqualTo(mockCategoryDTO());
    }

    @Test
    @DisplayName("Given valid dto, When mapped by mapper, Then Entity is Ok")
    public void passMapperFromValidDtoToEntity() {
        Category mappedEntity = mapper.categoryDTO(mockCategoryDTO());
        Assertions.assertThat(mappedEntity).isNotNull();
        Assertions.assertThat(mappedEntity.getName()).isEqualTo(mockCategory().getName());
        Assertions.assertThat(mappedEntity.getDescription()).isEqualTo(mockCategory().getDescription());
    }

    private Category mockCategory() {
        Category mock = new Category();
        mock.setName("Brød");
        mock.setDescription("Brød til alle måltider");
        mock.setUpdatedBy("Jens");
        mock.setCreatedBy("Majken");
        ReflectionTestUtils.setField(mock, "id", "12345");
        ReflectionTestUtils.setField(mock, "updated", OffsetDateTimeProvider.provide("2023-01-25T14:25:15"));
        ReflectionTestUtils.setField(mock, "created", OffsetDateTimeProvider.provide("2022-11-05T19:47:29"));
        return mock;
    }

    @Test
    @DisplayName("Given Unit is null, When mapped to DTO, Then DTO is Null")
    public void passMapperFromNullEntityToNullDto() {
        CategoryDTO mappedDto = mapper.category(null);
        Assertions.assertThat(mappedDto).isNull();
    }

    @Test
    @DisplayName("Given UnitDTO is null, When mapped to Entity, Then Entity is Null")
    public void passMapperFromNullDtoToNullEntity() {
        Category mappedDto = mapper.categoryDTO(null);
        Assertions.assertThat(mappedDto).isNull();
    }

    private CategoryDTO mockCategoryDTO() {
        CategoryDTO dto = CategoryDTO.builder()
                .id("12345")
                .name("Brød")
                .description("Brød til alle måltider")
                .createdBy("Majken")
                .updatedBy("Jens")
                .updated(OffsetDateTimeProvider.provide("2023-01-25T14:25:15"))
                .created(OffsetDateTimeProvider.provide("2022-11-05T19:47:29"))
                .build();
        return dto;
    }
}
