package com.application.picturecontest.application.usecase.findallphotographers;

import com.application.picturecontest.application.usecase.photographer.findallphotographers.FindAllPhotographersOutput;
import com.application.picturecontest.application.usecase.photographer.findallphotographers.FindAllPhotographersUseCase;
import com.application.picturecontest.domain.model.participant.Photographer;
import com.application.picturecontest.domain.model.valueobject.PersonInformation;
import com.application.picturecontest.domain.port.PhotographerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class FindAllPhotographersUseCaseTest {
    private PhotographerRepository repository;
    private FindAllPhotographersUseCase useCase;

    @BeforeEach
    void setUp() {
        repository = mock(PhotographerRepository.class);
        useCase = new FindAllPhotographersUseCase(repository);
    }

    @Test
    void execute_returnsAllPhotographers() {

        Photographer p1 = Photographer.create(TestData.info("a@test.com"));
        Photographer p2 = Photographer.create(TestData.info("b@test.com"));

        when(repository.findAll())
                .thenReturn(List.of(p1, p2));

        FindAllPhotographersOutput output = useCase.execute();

        assertNotNull(output);
        assertEquals(2, output.photographers().size());
        assertEquals(List.of(p1, p2), output.photographers());

        verify(repository).findAll();
    }

    @Test
    void execute_whenNoPhotographers_returnsEmptyList() {

        when(repository.findAll())
                .thenReturn(List.of());

        FindAllPhotographersOutput output = useCase.execute();

        assertNotNull(output);
        assertTrue(output.photographers().isEmpty());

        verify(repository).findAll();
    }

    /**
     * Helper interno de test para no ensuciar el test con builders largos
     */
    private static class TestData {

        static PersonInformation info(String email) {
            return new PersonInformation(
                    "Name",
                    "Lastname",
                    "Location",
                    email
            );
        }
    }
}