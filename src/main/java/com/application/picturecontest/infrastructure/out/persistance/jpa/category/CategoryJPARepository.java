package com.application.picturecontest.infrastructure.out.persistance.jpa.category;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CategoryJPARepository
        extends JpaRepository<CategoryEntity, UUID> {

}
