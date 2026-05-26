package com.enterpriseassignment.audit;

import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;

@Slf4j
public class AuditListener {

    @PrePersist
    public void prePersist(Object entity) {

        log.info("Creating entity: {} at {}",
                entity.getClass().getSimpleName(),
                LocalDateTime.now());
    }

    @PreUpdate
    public void preUpdate(Object entity) {

        log.info("Updating entity: {} at {}",
                entity.getClass().getSimpleName(),
                LocalDateTime.now());
    }
}