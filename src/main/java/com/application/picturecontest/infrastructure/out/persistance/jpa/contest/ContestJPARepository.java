package com.application.picturecontest.infrastructure.out.persistance.jpa.contest;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ContestJPARepository
        extends JpaRepository<ContestEntity, UUID> {

}
