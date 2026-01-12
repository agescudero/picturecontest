package com.application.picturecontest.application.usecase.addphotographerusecase;

import com.application.picturecontest.application.usecase.addphotographer.AddPhotographerUseCase;
import com.application.picturecontest.application.usecase.addphotographer.AddPhotographerInput;
import com.application.picturecontest.application.usecase.addphotographer.AddPhotographerOutput;
import com.application.picturecontest.domain.model.valueobject.PersonInformation;
import com.application.picturecontest.domain.port.PhotographerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class AddPhotographerUseCaseTest {

    private final PhotographerRepository repository = Mockito.mock(PhotographerRepository.class);

    @BeforeEach
    void setUp() {
        when(repository.save(any()))
                .thenReturn(UUID.randomUUID());
    }

    @Test
    void execute() {
        AddPhotographerUseCase useCase = new AddPhotographerUseCase(repository);

        String name = "name";
        String lastname = "lastname";
        String location = "location";
        String email = "email";

        AddPhotographerInput input = new AddPhotographerInput(name, lastname, location, email);

        AddPhotographerOutput output = useCase.execute(input);

        UUID id = output.id();

        assertNotNull(id);

    }

    private static PersonInformation getInfo(){
        String name = "name";
        String lastname = "lastname";
        String location = "location";
        String email = "email";

        return new PersonInformation(name, lastname, location, email);
    }
}