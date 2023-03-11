package dk.serik.recipes.model;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name="rating")
public class Rating extends BaseIdentifierEntity {
	
	@OneToMany(mappedBy = "rating")
	private Set<RecipeRating> recipeRatingEntities;
		
	private Integer rating;
	
	private String description;

}
