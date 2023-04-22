package dk.serik.recipes.mapper;

import dk.serik.recipes.dto.RatingDTO;
import dk.serik.recipes.model.Rating;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface RatingMapper {

    Rating ratingDtoToRating(RatingDTO ratingDTO);
    RatingDTO ratingToRatingDTO(Rating rating);
}
