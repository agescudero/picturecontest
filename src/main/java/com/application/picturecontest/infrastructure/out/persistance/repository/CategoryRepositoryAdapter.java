package com.application.picturecontest.infrastructure.out.persistance.repository;

import com.application.picturecontest.domain.model.contest.Category;
import com.application.picturecontest.domain.model.participant.Photographer;
import com.application.picturecontest.domain.port.CategoryRepository;
import com.application.picturecontest.domain.port.PhotographerRepository;
import com.application.picturecontest.infrastructure.out.persistance.jpa.category.CategoryEntity;
import com.application.picturecontest.infrastructure.out.persistance.jpa.category.CategoryJPARepository;
import com.application.picturecontest.infrastructure.out.persistance.jpa.category.CategoryMapper;
import com.application.picturecontest.infrastructure.out.persistance.jpa.photographer.PhotographerEntity;
import com.application.picturecontest.infrastructure.out.persistance.jpa.photographer.PhotographerJPARepository;
import com.application.picturecontest.infrastructure.out.persistance.jpa.photographer.PhotographerMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public class CategoryRepositoryAdapter implements CategoryRepository {

    public final CategoryJPARepository repository;

    public CategoryRepositoryAdapter(CategoryJPARepository repository) {
        this.repository = repository;
    }

    @Override
    public UUID save(Category category) {
        CategoryEntity entity = CategoryMapper.toEntity(category);
        CategoryEntity saved = repository.save(entity);
        return saved.getId();
    }

    @Override
    public Optional<Category> find(UUID id) {
        return repository
                .findById(id)
                .map(CategoryMapper::toDomain);
    }

    @Override
    public List<Category> findAll() {
        return repository
                .findAll()
                .stream()
                .map(CategoryMapper::toDomain)
                .toList();
    }
}
