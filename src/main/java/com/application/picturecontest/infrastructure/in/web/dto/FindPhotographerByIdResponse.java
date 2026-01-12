package com.application.picturecontest.infrastructure.in.web.dto;

import com.application.picturecontest.domain.model.participant.Photographer;
import com.application.picturecontest.domain.model.valueobject.ContestLevel;
import com.application.picturecontest.domain.model.valueobject.PersonInformation;

import java.util.UUID;

public record FindPhotographerByIdResponse(
    UUID id,
    String name,
    String lastname,
    String location,
    String email,
    ContestLevel level
) {
    public static FindPhotographerByIdResponse from(Photographer photographer) {
        PersonInformation info = photographer.getInformation();

        return new FindPhotographerByIdResponse(
                photographer.getId(),
                info.getName(),
                info.getLastname(),
                info.getLocation(),
                info.getEmail(),
                photographer.getLevel()
        );
    }

}
