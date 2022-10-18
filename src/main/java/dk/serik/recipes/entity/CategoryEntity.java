package dk.serik.recipes.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Data
@EqualsAndHashCode(callSuper=true)
@ToString(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Table(name="category")
public class CategoryEntity extends GenericEntity {
	
	// @Column(name="name", nullable = false)
	@Column(nullable = false)
	private String name;

	

	
	
}
