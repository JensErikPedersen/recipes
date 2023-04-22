package dk.serik.recipes.mapper;

import dk.serik.recipes.dto.TagDTO;
import dk.serik.recipes.model.Tag;
import dk.serik.recipes.mockutil.MockTagUtil;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

public class TagMapperTest {

    private TagMapper mapper = Mappers.getMapper(TagMapper.class);
    @Test
    @DisplayName("Given Entity is OK, When Map to DTO, Then Ok")
    public void passMapperFromValidEntityToDto() {
        TagDTO mappedDto = mapper.tagToTagDTO(MockTagUtil.mockTag());
        Assertions.assertThat(mappedDto).isNotNull();
        Assertions.assertThat(mappedDto).isEqualTo(MockTagUtil.mockTagDTO());
    }

    @Test
    @DisplayName("Given DTO is OK, When Map to Entity, Then Ok")
    public void passMapperFromValidDtoToEntity() {
        Tag mappedTo = mapper.tagDTOToTag((MockTagUtil.mockTagDTO()));
        Assertions.assertThat(mappedTo).isNotNull();
        Assertions.assertThat(mappedTo.getLabel()).isEqualTo(MockTagUtil.mockTag().getLabel());
    }

    @Test
    @DisplayName("Given Entity is null, When mapped to DTO, Then DTO is Null")
    public void passMapperFromNullEntityToNullDto() {
        TagDTO mappedDto = mapper.tagToTagDTO(null);
        Assertions.assertThat(mappedDto).isNull();
    }

    @Test
    @DisplayName("Given DTO is null, When mapped to Entity, Then Entity is Null")
    public void passMapperFromNullDtoToNullEntity() {
        Tag mappedTo = mapper.tagDTOToTag(null);
        Assertions.assertThat(mappedTo).isNull();
    }

}
