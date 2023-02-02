package dk.serik.recipes.model;

import java.time.OffsetDateTime;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import javax.persistence.PostLoad;
import javax.persistence.PostPersist;
import javax.persistence.PostRemove;
import javax.persistence.PostUpdate;
import javax.persistence.PrePersist;
import javax.persistence.PreRemove;
import javax.persistence.PreUpdate;
import javax.persistence.Transient;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;

@SuppressWarnings("rawtypes")
@Data
@MappedSuperclass
@EntityListeners(GenericEntityListener.class)
public class GenericEntity implements Comparable {

	    @Transient
	    protected final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(getClass());

	    @Setter(AccessLevel.PRIVATE)	    
	    @Column(nullable = false)
	    protected OffsetDateTime created;

	    @Column(nullable = false)
	    protected String createdBy;

	    @Setter(AccessLevel.PRIVATE)
	    protected OffsetDateTime updated;

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
	        if(o!=null && o instanceof GenericEntity && created!=null && ((GenericEntity) o).created!=null) {
	            return created.compareTo(((GenericEntity) o).created);
	        }
	        return -1;
	    }
}
