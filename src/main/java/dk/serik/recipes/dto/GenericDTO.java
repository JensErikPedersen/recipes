package dk.serik.recipes.dto;

import java.time.OffsetDateTime;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;

@Data
@JsonInclude(Include.NON_NULL)
public class GenericDTO {

	private String id;
	
	private OffsetDateTime created;

	private String createdBy;

	private OffsetDateTime updated;

	private String updatedBy;
}
