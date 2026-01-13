package com.application.picturecontest.application.usecase.becomepremium;

import com.application.picturecontest.application.usecase.photographer.becomepremium.BecomePremiumInput;
import com.application.picturecontest.application.usecase.photographer.becomepremium.BecomePremiumUseCase;
import com.application.picturecontest.domain.model.participant.Photographer;
import com.application.picturecontest.domain.model.valueobject.ContestLevel;
import com.application.picturecontest.domain.model.valueobject.PersonInformation;
import com.application.picturecontest.domain.port.PhotographerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class BecomePremiumUseCaseTest {

    private final PhotographerRepository repository = Mockito.mock(PhotographerRepository.class);
    private final static Photographer photographer = Photographer.create(getInfo());

    @BeforeEach
    void setUp() {
        when(repository.find(photographer.getId()))
                .thenReturn(Optional.of(photographer));
    }

    @Test
    void executeOk() {
        BecomePremiumUseCase useCasease = new BecomePremiumUseCase(repository);
        useCasease.execute(new BecomePremiumInput(photographer.getId()));
        assertEquals(ContestLevel.PREMIUM, photographer.getLevel());
    }

    @Test
    void executeNotFound() {
        BecomePremiumUseCase useCasease = new BecomePremiumUseCase(repository);
        Exception e = assertThrows(NoSuchElementException.class, () -> useCasease.execute(new BecomePremiumInput(UUID.randomUUID())));
        assertEquals("Photographer not found", e.getMessage());
    }

    private static PersonInformation getInfo() {
        String name = "name";
        String lastname = "lastname";
        String location = "location";
        String email = "email";

        return new PersonInformation(name, lastname, location, email);
    }
}