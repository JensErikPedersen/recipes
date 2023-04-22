package dk.serik.recipes.mapper;


import dk.serik.recipes.dto.RecipeRatingDTO;
import dk.serik.recipes.mockutil.MockRecipeRatingDTOUtil;
import dk.serik.recipes.model.RecipeRating;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import dk.serik.recipes.mockutil.MockRecipeRatingUtil;

public class RecipeRatingMapperTest {

    private RecipeRatingMapper mapper = Mappers.getMapper(RecipeRatingMapper.class);

    @Test
    @DisplayName("Given valid entity, When mapped by mapper, Then DTO is Ok")
    public void passMapperFromValidEntityToDto() {
        RecipeRatingDTO mappedDto = mapper.recipeRatingToRecipeRatingDTO(MockRecipeRatingUtil.mockRecipeHvedebreadRating1());
        Assertions.assertThat(mappedDto).isNotNull();
        Assertions.assertThat(mappedDto).isEqualTo(MockRecipeRatingDTOUtil.mockRecipeHvedebreadRatingDTO1());
    }

    @Test
    @DisplayName("Given valid dto, When mapped by mapper, Then Entity is Ok")
    public void passMapperFromValidDtoToEntity() {
        RecipeRating mappedEntity = mapper.recipeRatingDTOToRecipeRating(MockRecipeRatingDTOUtil.mockRecipeHvedebreadRatingDTO1());
        Assertions.assertThat(mappedEntity).isNotNull();
        Assertions.assertThat(mappedEntity.getRating()).isEqualTo(MockRecipeRatingUtil.mockRecipeHvedebreadRating1().getRating());
        Assertions.assertThat(mappedEntity.getDescription()).isEqualTo(MockRecipeRatingUtil.mockRecipeHvedebreadRating1().getDescription());
    }


    @Test
    @DisplayName("Given Unit is null, When mapped to DTO, Then DTO is Null")
    public void passMapperFromNullEntityToNullDto() {
        RecipeRatingDTO mappedDto = mapper.recipeRatingToRecipeRatingDTO(null);
        Assertions.assertThat(mappedDto).isNull();
    }

    @Test
    @DisplayName("Given UnitDTO is null, When mapped to Entity, Then Entity is Null")
    public void passMapperFromNullDtoToNullEntity() {
        RecipeRating mappedDto = mapper.recipeRatingDTOToRecipeRating(null);
        Assertions.assertThat(mappedDto).isNull();
    }

}
