package com.application.picturecontest.infrastructure.out.persistance.repository;

import com.application.picturecontest.domain.model.contest.Contest;
import com.application.picturecontest.domain.model.participant.Photographer;
import com.application.picturecontest.domain.port.ContestRepository;
import com.application.picturecontest.domain.port.PhotographerRepository;
import com.application.picturecontest.infrastructure.out.persistance.jpa.contest.ContestEntity;
import com.application.picturecontest.infrastructure.out.persistance.jpa.contest.ContestJPARepository;
import com.application.picturecontest.infrastructure.out.persistance.jpa.contest.ContestMapper;
import com.application.picturecontest.infrastructure.out.persistance.jpa.photographer.PhotographerEntity;
import com.application.picturecontest.infrastructure.out.persistance.jpa.photographer.PhotographerJPARepository;
import com.application.picturecontest.infrastructure.out.persistance.jpa.photographer.PhotographerMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public class ContestRepositoryAdapter implements ContestRepository {

    public final ContestJPARepository repository;

    public ContestRepositoryAdapter(ContestJPARepository repository) {
        this.repository = repository;
    }

    @Override
    public UUID save(Contest c) {
        ContestEntity entity = ContestMapper.toEntity(c);
        ContestEntity saved = repository.save(entity);
        return saved.getId();
    }

    @Override
    public Optional<Contest> find(UUID id) {
        return repository
                .findById(id)
                .map(ContestMapper::toDomain);
    }
}
