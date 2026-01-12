package com.application.picturecontest.application.usecase.findallphotographers;

import com.application.picturecontest.application.usecase.findphotographerbyid.FindPhotographerByIdInput;
import com.application.picturecontest.application.usecase.findphotographerbyid.FindPhotographerByIdOutput;
import com.application.picturecontest.domain.model.participant.Photographer;
import com.application.picturecontest.domain.port.PhotographerRepository;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Optional;

public class FindAllPhotographersUseCase {

    private final PhotographerRepository repository;


    public FindAllPhotographersUseCase(PhotographerRepository repository) {
        this.repository = repository;
    }

    public FindAllPhotographersOutput execute() {

        List<Photographer> result = repository.findAll();
        return new FindAllPhotographersOutput(result);
    }
}
