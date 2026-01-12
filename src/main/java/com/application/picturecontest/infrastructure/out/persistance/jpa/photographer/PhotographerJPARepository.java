package com.application.picturecontest.infrastructure.out.persistance.jpa.photographer;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface PhotographerJPARepository
        extends JpaRepository<PhotographerEntity, UUID> {

}
