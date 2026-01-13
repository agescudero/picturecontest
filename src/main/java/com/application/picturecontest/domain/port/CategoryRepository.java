package com.application.picturecontest.domain.port;

import com.application.picturecontest.domain.model.contest.Category;
import com.application.picturecontest.domain.model.participant.Photographer;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CategoryRepository {

    UUID save(Category p);
    Optional<Category> find(UUID id);
    List<Category> findAll();

}
