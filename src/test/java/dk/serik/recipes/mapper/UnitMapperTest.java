package dk.serik.recipes.mapper;

import dk.serik.recipes.dto.UnitDTO;
import dk.serik.recipes.model.Unit;
import dk.serik.recipes.testutil.OffsetDateTimeProvider;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import org.springframework.test.util.ReflectionTestUtils;

public class UnitMapperTest {

    private UnitMapper mapper = Mappers.getMapper(UnitMapper.class);
    @Test
    @DisplayName("Given Unit is OK, When Map to DTO, Then Ok")
    public void passMapperFromValidEntityToDto() {
        UnitDTO mappedDto = mapper.unit(mockUnit());
        Assertions.assertThat(mappedDto).isNotNull();
        Assertions.assertThat(mappedDto).isEqualTo(mockUnitDTO());
    }

    @Test
    @DisplayName("Given UnitDTO is OK, When Map to Entity, Then Ok")
    public void passMapperFromValidDtoToEntity() {
        Unit mappedTo = mapper.unitDto((mockUnitDTO()));
        Assertions.assertThat(mappedTo).isNotNull();
        Assertions.assertThat(mappedTo.getLabel()).isEqualTo(mockUnit().getLabel());
        Assertions.assertThat(mappedTo.getDescription()).isEqualTo(mockUnit().getDescription());
    }

    @Test
    @DisplayName("Given Unit is null, When mapped to DTO, Then DTO is Null")
    public void passMapperFromNullEntityToNullDto() {
        UnitDTO mappedDto = mapper.unit(null);
        Assertions.assertThat(mappedDto).isNull();
    }

    @Test
    @DisplayName("Given UnitDTO is null, When mapped to Entity, Then Entity is Null")
    public void passMapperFromNullDtoToNullEntity() {
        Unit mappedDto = mapper.unitDto(null);
        Assertions.assertThat(mappedDto).isNull();
    }

    private Unit mockUnit() {
        Unit mock = new Unit();
        mock.setDescription("Deciliter");
        mock.setLabel("dl");
        mock.setCreatedBy("Majken");
        mock.setUpdatedBy("Jens");
        ReflectionTestUtils.setField(mock, "id", "12345");
        ReflectionTestUtils.setField(mock, "updated", OffsetDateTimeProvider.provide("2023-01-25T14:25:15"));
        ReflectionTestUtils.setField(mock, "created", OffsetDateTimeProvider.provide("2022-11-05T19:47:29"));
        return mock;
    }

    private UnitDTO mockUnitDTO() {
        UnitDTO mock = UnitDTO.builder()
                .id("12345")
                .createdBy("Majken")
                .created(OffsetDateTimeProvider.provide("2022-11-05T19:47:29"))
                .updatedBy("Jens")
                .updated(OffsetDateTimeProvider.provide("2023-01-25T14:25:15"))
                .label("dl")
                .description("Deciliter")
                .build();
        return mock;
    }
}
