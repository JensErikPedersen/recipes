package dk.serik.recipes.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tag")
public class Tag extends BaseIdentifierEntity {
	
	@OneToMany(mappedBy= "tag")
	private Set<RecipeTag> recipeTagEntities;
	
	@Column(nullable = false)	
	private String label;
}
