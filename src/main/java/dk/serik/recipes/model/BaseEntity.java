package dk.serik.recipes.model;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.io.Serializable;
import java.time.OffsetDateTime;

@SuppressWarnings("rawtypes")
@Data
@MappedSuperclass
@EntityListeners(BaseEntityListener.class)
@Slf4j
public class BaseEntity implements Comparable, Serializable {

	    @Setter(AccessLevel.PRIVATE)	    
	    @Column(nullable = false)
	    protected OffsetDateTime created;

	    @Column(nullable = false)
	    protected String createdBy;
		@Setter(AccessLevel.PRIVATE)
		@Column(nullable = false)
	    protected OffsetDateTime updated;

		@Column(nullable = false)
	    protected String updatedBy;

	    @PrePersist
	    public void prePersist() {
	        setCreated(OffsetDateTime.now());
	    }
	    @PostPersist
	    public void postPersist() {
	    }
	    @PreRemove
	    public void preRemove() {
	    }
	    @PostRemove
	    public void postRemove() {
	    }
	    @PreUpdate
	    public void preUpdate() {
	    	setUpdated(OffsetDateTime.now());	        
	    }
	    @PostUpdate
	    public void postUpdate() {
	    }
	    @PostLoad
	    public void postLoad() {
	    }

	    @Override
	    public int compareTo(Object o) {
	        if(o!=null && o instanceof BaseEntity && created!=null && ((BaseEntity) o).created!=null) {
	            return created.compareTo(((BaseEntity) o).created);
	        }
	        return -1;
	    }
}
