package com.application.picturecontest.infrastructure.out.persistance.repository;

import com.application.picturecontest.domain.model.participant.Photographer;
import com.application.picturecontest.domain.port.PhotographerRepository;
import com.application.picturecontest.infrastructure.out.persistance.jpa.photographer.PhotographerEntity;
import com.application.picturecontest.infrastructure.out.persistance.jpa.photographer.PhotographerJPARepository;
import com.application.picturecontest.infrastructure.out.persistance.jpa.photographer.PhotographerMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public class PhotographerRepositoryAdapter implements PhotographerRepository {

    public final PhotographerJPARepository repository;

    public PhotographerRepositoryAdapter(PhotographerJPARepository repository) {
        this.repository = repository;
    }

    @Override
    public UUID save(Photographer p) {
        PhotographerEntity entity = PhotographerMapper.toEntity(p);
        PhotographerEntity saved = repository.save(entity);
        return saved.getId();
    }

    @Override
    public Optional<Photographer> find(UUID id) {
        return repository
                .findById(id)
                .map(PhotographerMapper::toDomain);
    }

    @Override
    public List<Photographer> findAll() {
        return repository
                .findAll()
                .stream()
                .map(PhotographerMapper::toDomain)
                .toList();
    }
}
