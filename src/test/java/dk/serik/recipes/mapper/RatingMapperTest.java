package dk.serik.recipes.mapper;

import dk.serik.recipes.dto.RatingDTO;
import dk.serik.recipes.mockutil.MockRatingDTOUtil;
import dk.serik.recipes.model.Rating;
import dk.serik.recipes.mockutil.MockRatingUtil;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

public class RatingMapperTest {

    private RatingMapper mapper = Mappers.getMapper(RatingMapper.class);
    @Test
    @DisplayName("Given valid entity, When mapped by mapper, Then DTO is Ok")
    public void passMapperFromValidEntityToDto() {
        RatingDTO mappedDto = mapper.ratingToRatingDTO(MockRatingUtil.mockRating5());
        Assertions.assertThat(mappedDto).isNotNull();
        Assertions.assertThat(mappedDto).isEqualTo(MockRatingDTOUtil.mockRatingDTO5());
    }

    @Test
    @DisplayName("Given valid dto, When mapped by mapper, Then Entity is Ok")
    public void passMapperFromValidDtoToEntity() {
        Rating mappedEntity = mapper.ratingDtoToRating(MockRatingDTOUtil.mockRatingDTO5());
        Assertions.assertThat(mappedEntity).isNotNull();
        Assertions.assertThat(mappedEntity.getRating()).isEqualTo(MockRatingUtil.mockRating5().getRating());
        Assertions.assertThat(mappedEntity.getDescription()).isEqualTo(MockRatingUtil.mockRating5().getDescription());
    }


    @Test
    @DisplayName("Given Unit is null, When mapped to DTO, Then DTO is Null")
    public void passMapperFromNullEntityToNullDto() {
        RatingDTO mappedDto = mapper.ratingToRatingDTO(null);
        Assertions.assertThat(mappedDto).isNull();
    }

    @Test
    @DisplayName("Given UnitDTO is null, When mapped to Entity, Then Entity is Null")
    public void passMapperFromNullDtoToNullEntity() {
        Rating mappedDto = mapper.ratingDtoToRating(null);
        Assertions.assertThat(mappedDto).isNull();
    }

}
