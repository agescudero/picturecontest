package com.application.picturecontest.domain.port;

import com.application.picturecontest.domain.model.participant.Photographer;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface PhotographerRepository {

    UUID save(Photographer p);
    Optional<Photographer> find(UUID id);
    List<Photographer> findAll();

}
