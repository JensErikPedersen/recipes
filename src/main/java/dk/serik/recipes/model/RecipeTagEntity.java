package dk.serik.recipes.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
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
@AllArgsConstructor
@NoArgsConstructor
@IdClass(RecipeTagPK.class)
@Table(name="recipe_tag")
public class RecipeTagEntity extends GenericEntity {
	
	@Id
	@ManyToOne
	@JoinColumn(name="recipe_id", nullable = false)
	private RecipeEntity recipeEntity;
	
	@Id
	@ManyToOne
	@JoinColumn(name="tag_id", nullable = false)
	private TagEntity tagEntity;
		

}
