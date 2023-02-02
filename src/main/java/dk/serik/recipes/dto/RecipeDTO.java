package dk.serik.recipes.dto;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;
import lombok.NoArgsConstructor;

@JsonInclude(Include.NON_NULL)
@NoArgsConstructor
@Data
public class RecipeDTO extends GenericDTO {

private String name;
	
	private String description;
	
	private String instructions;
}
