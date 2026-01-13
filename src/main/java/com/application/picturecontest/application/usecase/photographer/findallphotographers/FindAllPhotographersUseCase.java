package com.application.picturecontest.application.usecase.photographer.findallphotographers;

import com.application.picturecontest.domain.model.participant.Photographer;
import com.application.picturecontest.domain.port.PhotographerRepository;

import java.util.List;

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
