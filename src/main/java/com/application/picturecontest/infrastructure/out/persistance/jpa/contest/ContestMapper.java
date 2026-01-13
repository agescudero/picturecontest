package com.application.picturecontest.infrastructure.out.persistance.jpa.contest;

import com.application.picturecontest.domain.model.contest.Contest;
import com.application.picturecontest.domain.model.participant.Photographer;
import com.application.picturecontest.domain.model.valueobject.ContestPrice;
import com.application.picturecontest.domain.model.valueobject.PersonInformation;
import com.application.picturecontest.infrastructure.out.persistance.jpa.photographer.PhotographerEntity;

public class ContestMapper {

    public static ContestEntity toEntity(Contest domain) {
        ContestPrice price = domain.getPrice();
        return new ContestEntity(
                domain.getId(),
                new ContestPriceEmbeddable(price.amount(), price.currency()),
                domain.getPhotographers(),
                domain.getCategories()
        );
    }

    public static Contest toDomain(ContestEntity entity) {
        ContestPriceEmbeddable price = entity.getPrice();
        return Contest.of(
                entity.getId(),
                new ContestPrice(price.getAmount(), price.getCurrency()),
                entity.getPhotographers(),
                entity.getCategories()
        );
    }

}
