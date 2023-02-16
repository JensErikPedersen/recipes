package dk.serik.recipes.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@IdClass(RecipeTagPK.class)
@Table(name="recipe_tag")
public class RecipeTag extends BaseEntity {
	
	@Id
	@ManyToOne
	@JoinColumn(name="recipe_id", nullable = false)
	private Recipe recipe;
	
	@Id
	@ManyToOne
	@JoinColumn(name="tag_id", nullable = false)
	private Tag tag;
		

}
