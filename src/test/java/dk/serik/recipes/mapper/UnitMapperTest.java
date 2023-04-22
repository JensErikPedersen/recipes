package dk.serik.recipes.mapper;

import dk.serik.recipes.dto.UnitDTO;
import dk.serik.recipes.model.Unit;
import dk.serik.recipes.mockutil.MockUnitUtil;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

public class UnitMapperTest {

    private UnitMapper mapper = Mappers.getMapper(UnitMapper.class);
    @Test
    @DisplayName("Given Unit is OK, When Map to DTO, Then Ok")
    public void passMapperFromValidEntityToDto() {
        UnitDTO mappedDto = mapper.unitToUnitDTO(MockUnitUtil.mockDl());
        Assertions.assertThat(mappedDto).isNotNull();
        Assertions.assertThat(mappedDto).isEqualTo(MockUnitUtil.mockUnitDeciliterDTO());
    }

    @Test
    @DisplayName("Given UnitDTO is OK, When Map to Entity, Then Ok")
    public void passMapperFromValidDtoToEntity() {
        Unit mappedTo = mapper.unitDTOToUnit(MockUnitUtil.mockUnitDeciliterDTO());
        Assertions.assertThat(mappedTo).isNotNull();
        Assertions.assertThat(mappedTo.getLabel()).isEqualTo(MockUnitUtil.mockDl().getLabel());
        Assertions.assertThat(mappedTo.getDescription()).isEqualTo(MockUnitUtil.mockDl().getDescription());
    }

    @Test
    @DisplayName("Given Unit is null, When mapped to DTO, Then DTO is Null")
    public void passMapperFromNullEntityToNullDto() {
        UnitDTO mappedDto = mapper.unitToUnitDTO(null);
        Assertions.assertThat(mappedDto).isNull();
    }

    @Test
    @DisplayName("Given UnitDTO is null, When mapped to Entity, Then Entity is Null")
    public void passMapperFromNullDtoToNullEntity() {
        Unit mappedDto = mapper.unitDTOToUnit(null);
        Assertions.assertThat(mappedDto).isNull();
    }

}
