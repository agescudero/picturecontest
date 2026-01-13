package com.application.picturecontest.domain.port;

import com.application.picturecontest.domain.model.contest.Contest;
import com.application.picturecontest.domain.model.participant.Photographer;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ContestRepository {

    UUID save(Contest c);
    Optional<Contest> find(UUID id);
}
