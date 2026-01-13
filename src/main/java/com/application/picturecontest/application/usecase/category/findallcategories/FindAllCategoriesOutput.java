package com.application.picturecontest.application.usecase.category.findallcategories;

import com.application.picturecontest.domain.model.contest.Category;
import com.application.picturecontest.domain.model.participant.Photographer;

import java.util.List;

public record FindAllCategoriesOutput(List<Category> categories) {
}
