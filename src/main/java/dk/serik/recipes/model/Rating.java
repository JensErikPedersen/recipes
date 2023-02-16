package dk.serik.recipes.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
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
