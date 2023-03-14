package dk.serik.recipes.mapper;

import dk.serik.recipes.dto.UnitDTO;
import dk.serik.recipes.model.Unit;
import org.mapstruct.Mapper;

@Mapper
public interface UnitMapper {
    Unit unitDto(UnitDTO unitDTO);
    UnitDTO unit(Unit unit);
}
