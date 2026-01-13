package com.application.picturecontest.infrastructure.in.web.dto.category;

import com.application.picturecontest.domain.model.contest.Category;
import com.application.picturecontest.domain.model.participant.Photographer;
import com.application.picturecontest.domain.model.valueobject.ContestLevel;
import com.application.picturecontest.domain.model.valueobject.PersonInformation;

import java.util.UUID;

public record FindCategoryByIdResponse(
    UUID id,
    String name,
    String description,
    ContestLevel level
) {
    public static FindCategoryByIdResponse from(Category category) {

        return new FindCategoryByIdResponse(
                category.getId(),
                category.getName(),
                category.getDescription(),
                category.getLevel()
        );
    }

}
