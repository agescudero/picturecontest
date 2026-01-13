package com.application.picturecontest.infrastructure.in.web.dto.category;

import com.application.picturecontest.domain.model.contest.Category;
import com.application.picturecontest.domain.model.participant.Photographer;

import java.util.List;

public record FindAllCategoriesResponse(List<Category> categories) {
}
