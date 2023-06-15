package dk.serik.recipes.model;

import jakarta.persistence.*;
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
@Table(name = "tag")
public class Tag extends BaseIdentifierEntity {
	@ManyToMany(mappedBy = "tags")
	private Set<Recipe> recipes;
	
	@Column(nullable = false)
	private String name;
}
