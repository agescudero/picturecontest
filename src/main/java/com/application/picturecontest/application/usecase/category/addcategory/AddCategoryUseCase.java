package com.application.picturecontest.application.usecase.category.addcategory;

import com.application.picturecontest.domain.model.contest.Category;
import com.application.picturecontest.domain.port.CategoryRepository;

import java.util.UUID;

public class AddCategoryUseCase {

    private final CategoryRepository repository;

    public AddCategoryUseCase(CategoryRepository repository) {
        this.repository = repository;
    }

    public AddCategoryOutput execute(AddCategoryInput input) {
        UUID savedId = repository.save(
                Category.create(input.name(),
                        input.description(),
                        input.level())
        );

        return new AddCategoryOutput(savedId);
    }
}
