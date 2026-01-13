package com.application.picturecontest.application.usecase.findphotographerbyid;

import com.application.picturecontest.application.usecase.photographer.findphotographerbyid.FindPhotographerByIdInput;
import com.application.picturecontest.application.usecase.photographer.findphotographerbyid.FindPhotographerByIdOutput;
import com.application.picturecontest.application.usecase.photographer.findphotographerbyid.FindPhotographerByIdUseCase;
import com.application.picturecontest.domain.model.participant.Photographer;
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

class FindPhotographerByIdUseCaseTest {

    private final PhotographerRepository repository = Mockito.mock(PhotographerRepository.class);
    private final static Photographer photographer = Photographer.create(getInfo());

    @BeforeEach
    void setUp() {
        when(repository.find(photographer.getId()))
                .thenReturn(Optional.of(photographer));
    }

    @Test
    void executeOK() {
        FindPhotographerByIdUseCase useCase = new FindPhotographerByIdUseCase(repository);
        UUID id = photographer.getId();
        FindPhotographerByIdOutput result = useCase.execute(new FindPhotographerByIdInput(id));

        assertNotNull(result);
        assertEquals(id, result.photographer().getId());
    }

    @Test
    void executeNotFound() {
        FindPhotographerByIdUseCase useCase = new FindPhotographerByIdUseCase(repository);
        UUID id = UUID.randomUUID();
        Exception e =assertThrows(NoSuchElementException.class, () -> useCase.execute(new FindPhotographerByIdInput(id)));
        assertEquals("Photographer not found", e.getMessage());
    }

    @Test
    void executeNoPhotographerId() {
        FindPhotographerByIdUseCase useCase = new FindPhotographerByIdUseCase(repository);
        UUID id = UUID.randomUUID();
        Exception e =assertThrows(NoSuchElementException.class, () -> useCase.execute(new FindPhotographerByIdInput(null)));
        assertEquals("Photographer not found", e.getMessage());
    }

    @Test
    void executeNoInputData() {
        FindPhotographerByIdUseCase useCase = new FindPhotographerByIdUseCase(repository);
        UUID id = UUID.randomUUID();
        Exception e =assertThrows(IllegalArgumentException.class, () -> useCase.execute(null));
        assertEquals("Input cannot be null", e.getMessage());
    }

    private static PersonInformation getInfo() {
        String name = "name";
        String lastname = "lastname";
        String location = "location";
        String email = "email";

        return new PersonInformation(name, lastname, location, email);
    }

}