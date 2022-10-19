package dk.serik.recipes.entity;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data
@EqualsAndHashCode(callSuper=true)
@ToString(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Table(name="unit")
public class UnitEntity extends GenericEntity {
	
	@Column(nullable = false)
	private String label;
	
	@Column(nullable = false)
	private String description;

}
