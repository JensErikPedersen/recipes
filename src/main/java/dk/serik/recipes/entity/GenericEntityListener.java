package dk.serik.recipes.entity;

import javax.persistence.PostLoad;
import javax.persistence.PostPersist;
import javax.persistence.PostRemove;
import javax.persistence.PostUpdate;
import javax.persistence.PrePersist;
import javax.persistence.PreRemove;
import javax.persistence.PreUpdate;

import org.springframework.stereotype.Service;

import dk.serik.recipes.Session;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@NoArgsConstructor
@AllArgsConstructor
public class GenericEntityListener {
	
	private Session session;

    @PrePersist
    public void prePersist(GenericEntity genericEntity) {
        genericEntity.setCreatedBy(session.getUserName());  
        log.info("createdBy is set to: {}", session.getUserName());
    }

    @PostPersist
    public void postPersist(GenericEntity genericEntity) {
        //log.info(sessionAware.getSessionId() + " : postPersist(): entity=" + genericEntity.getClass().getSimpleName() + " : " + genericEntity.getEntityId());
    }
    @PreRemove
    public void preRemove(GenericEntity genericEntity) {
        //log.info(sessionAware.getSessionId() + " : preRemove(): entity=" + genericEntity.getClass().getSimpleName() + " : " + genericEntity.getEntityId());
    }
    @PostRemove
    public void postRemove(GenericEntity genericEntity) {
        //log.info(sessionAware.getSessionId() + " : postRemove(): entity=" + genericEntity.getClass().getSimpleName() + " : " + genericEntity.getEntityId());
    }
    @PreUpdate
    public void preUpdate(GenericEntity genericEntity) {
        genericEntity.setUpdatedBy(session.getUserName());
        log.info("updateBy is set to: {}", session.getUserName());
    }

    @PostUpdate
    public void postUpdate(GenericEntity genericEntity) {
        //log.info(sessionAware.getSessionId() + " : postUpdate(): entity=" + genericEntity.getClass().getSimpleName() + " : " + genericEntity.getEntityId());
    }
    @PostLoad
    public void postLoad(GenericEntity genericEntity) {
        //log.info(sessionAware.getSessionId() + " : postLoad(): entity=" + genericEntity.getClass().getSimpleName() + " : " + genericEntity.getEntityId());
    }
}
