package com.application.picturecontest.application.usecase.category.addcategory;

import com.application.picturecontest.domain.model.valueobject.ContestLevel;

public record AddCategoryInput(String name, String description, ContestLevel level) {
}