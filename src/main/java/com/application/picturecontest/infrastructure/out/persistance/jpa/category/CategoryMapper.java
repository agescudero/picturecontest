package com.application.picturecontest.infrastructure.out.persistance.jpa.category;

import com.application.picturecontest.domain.model.contest.Category;
import com.application.picturecontest.domain.model.participant.Photographer;
import com.application.picturecontest.domain.model.valueobject.PersonInformation;
import com.application.picturecontest.infrastructure.out.persistance.jpa.photographer.PhotographerEntity;

public class CategoryMapper {

    public static CategoryEntity toEntity(Category domain) {
        return new CategoryEntity(
                domain.getId(),
                domain.getName(),
                domain.getDescription(),
                domain.getLevel()
        );
    }

    public static Category toDomain(CategoryEntity entity) {

        return Category.of(
                entity.getId(),
                entity.getName(),
                entity.getDescription(),
                entity.getLevel()
        );
    }

}
