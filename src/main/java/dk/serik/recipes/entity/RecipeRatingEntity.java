package dk.serik.recipes.entity;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "recipe_rating")
public class RecipeRatingEntity extends GenericIdentifierEntity {

	@ManyToOne
    @JoinColumn(name = "recipe_id", nullable = false )
	private RecipeEntity recipeEntity;
	
	@ManyToOne
    @JoinColumn(name = "course_id", nullable = false)
	private RatingEntity ratingEntity;
	
	private String description;
	
}
