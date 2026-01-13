package com.application.picturecontest.application.usecase.category.findallcategories;

import com.application.picturecontest.domain.model.contest.Category;
import com.application.picturecontest.domain.port.CategoryRepository;

import java.util.List;

public class FindAllCategoriesUseCase {

    private final CategoryRepository repository;


    public FindAllCategoriesUseCase(CategoryRepository repository) {
        this.repository = repository;
    }

    public FindAllCategoriesOutput execute() {
        List<Category> result = repository.findAll();
        return new FindAllCategoriesOutput(result);
    }
}
