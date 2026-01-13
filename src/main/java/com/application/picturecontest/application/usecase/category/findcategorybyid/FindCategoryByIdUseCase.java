package com.application.picturecontest.application.usecase.category.findcategorybyid;

import com.application.picturecontest.domain.model.contest.Category;
import com.application.picturecontest.domain.port.CategoryRepository;

import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Optional;

public class FindCategoryByIdUseCase {

    private final CategoryRepository repository;


    public FindCategoryByIdUseCase(CategoryRepository repository) {
        this.repository = repository;
    }

    public FindCategoryByIdOutput execute(FindCategoryByIdInput input) {

        if (Objects.isNull(input)) throw new IllegalArgumentException("Input cannot be null");
        Optional<Category> result = repository.find(input.id());
        Category p = result.orElseThrow(() -> new NoSuchElementException("Category not found"));
        return new FindCategoryByIdOutput(p);
    }
}
