package dk.serik.recipes.model;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Objects;

@SuppressWarnings("serial")
@Embeddable
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class RecipeTagPK implements Serializable {
	
	private String recipe;
	
	private String tag;

	@Override
	public int hashCode() {
		return Objects.hash(recipe, tag);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		RecipeTagPK other = (RecipeTagPK) obj;
		return Objects.equals(recipe, other.recipe) && Objects.equals(tag, other.tag);
	}

	
}
