package dk.serik.recipes.mapper;

import dk.serik.recipes.dto.UnitDTO;
import dk.serik.recipes.model.Unit;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UnitMapper {
    Unit unitDTOToUnit(UnitDTO unitDTO);
    UnitDTO unitToUnitDTO(Unit unit);
}
