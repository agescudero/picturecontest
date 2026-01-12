package com.application.picturecontest.infrastructure.out.persistance.jpa.photographer;

import com.application.picturecontest.domain.model.participant.Photographer;
import com.application.picturecontest.domain.model.valueobject.PersonInformation;

public class PhotographerMapper {

    public static PhotographerEntity toEntity(Photographer domain) {
        PersonInformation info = domain.getInformation();
        return new PhotographerEntity(
                domain.getId(),
                info.getName(),
                info.getLastname(),
                info.getLocation(),
                info.getEmail(),
                domain.getLevel()
        );
    }

    public static Photographer toDomain(PhotographerEntity entity) {
        PersonInformation info = new PersonInformation(entity.getName(),
                entity.getLastname(),
                entity.getLocation(),
                entity.getEmail()
        );

        return Photographer.of(
                entity.getId(),
                info,
                null,
                entity.getLevel()
        );
    }

}
