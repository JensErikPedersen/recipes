package dk.serik.recipes.model;

import java.util.Set;

import javax.persistence.Column;
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
@Table(name = "tag")
public class TagEntity extends GenericIdentifierEntity {
	
	@OneToMany(mappedBy="tagEntity")
	private Set<RecipeTagEntity> recipeTagEntities;
	
	@Column(nullable = false)	
	private String label;
}
