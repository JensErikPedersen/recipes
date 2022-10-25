package dk.serik.recipes.entity;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name="rating")
public class RatingEntity extends GenericIdentifierEntity {
	
	@OneToMany(mappedBy = "ratingEntity")
	private Set<RecipeRatingEntity> recipeRatingEntities;
		
	private Integer rating;
	
	private String description;

}
