package dk.serik.recipes.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "recipe_rating")
public class RecipeRating extends BaseIdentifierEntity {

	@ManyToOne
    @JoinColumn(name = "recipe_id", nullable = false )
	private Recipe recipe;
	
	@ManyToOne
    @JoinColumn(name = "rating_id", nullable = false)
	private Rating rating;
	
	private String description;
	
}
