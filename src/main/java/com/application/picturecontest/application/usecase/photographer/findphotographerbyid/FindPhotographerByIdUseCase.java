package com.application.picturecontest.application.usecase.photographer.findphotographerbyid;

import com.application.picturecontest.domain.model.participant.Photographer;
import com.application.picturecontest.domain.port.PhotographerRepository;

import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Optional;

public class FindPhotographerByIdUseCase {

    private final PhotographerRepository repository;


    public FindPhotographerByIdUseCase(PhotographerRepository repository) {
        this.repository = repository;
    }

    public FindPhotographerByIdOutput execute(FindPhotographerByIdInput input) {

        if (Objects.isNull(input)) throw new IllegalArgumentException("Input cannot be null");
        Optional<Photographer> result = repository.find(input.id());
        Photographer p = result.orElseThrow(() -> new NoSuchElementException("Photographer not found"));
        return new FindPhotographerByIdOutput(p);
    }
}
