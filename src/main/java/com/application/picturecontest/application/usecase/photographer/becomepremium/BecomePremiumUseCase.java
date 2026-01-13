package com.application.picturecontest.application.usecase.photographer.becomepremium;

import com.application.picturecontest.domain.model.participant.Photographer;
import com.application.picturecontest.domain.port.PhotographerRepository;

import java.util.NoSuchElementException;
import java.util.Optional;

public class BecomePremiumUseCase {

    private final PhotographerRepository repository;


    public BecomePremiumUseCase(PhotographerRepository repository) {
        this.repository = repository;
    }

    public void execute(BecomePremiumInput input) {
        Optional<Photographer> result = repository.find(input.id());
        Photographer photographer = result.orElseThrow((() -> new NoSuchElementException("Photographer not found")));
        photographer.becomePremium();
        repository.save(photographer);
    }
}
