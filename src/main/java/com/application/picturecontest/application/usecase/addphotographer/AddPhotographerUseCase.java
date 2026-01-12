package com.application.picturecontest.application.usecase.addphotographer;

import com.application.picturecontest.domain.model.participant.Photographer;
import com.application.picturecontest.domain.model.valueobject.PersonInformation;
import com.application.picturecontest.domain.port.PhotographerRepository;

import java.util.UUID;

public class AddPhotographerUseCase {

    private final PhotographerRepository repository;


    public AddPhotographerUseCase(PhotographerRepository repository) {
        this.repository = repository;
    }

    public AddPhotographerOutput execute(AddPhotographerInput input) {

        PersonInformation info = new PersonInformation(
                input.name(),
                input.lastname(),
                input.location(),
                input.email()
        );

        UUID savedId = repository.save(Photographer.create(info));

        return new AddPhotographerOutput(savedId);
    }
}
