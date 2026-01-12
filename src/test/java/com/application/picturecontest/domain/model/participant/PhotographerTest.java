package com.application.picturecontest.domain.model.participant;

import com.application.picturecontest.domain.model.valueobject.ContestLevel;
import com.application.picturecontest.domain.model.valueobject.PersonInformation;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PhotographerTest {

    @Test
    void newPhotographerCreated(){
        PersonInformation info = new PersonInformation(
                "name",
                "lastname",
                "location",
                "email"
        );

        Photographer photographer = Photographer.create(info);
        assertEquals(info, photographer.getInformation());
        assertEquals(0, photographer.getPhotos().size());
    }

    @Test
    void newPhotographerCreatedWithNoPersonInformation(){
        Exception exception = assertThrows(NullPointerException.class, () -> Photographer.create(null));
        assertEquals("Person Information cannot be null", exception.getMessage());
    }

    @Test
    void photographerUpdatesPersonInformation(){
        PersonInformation info = new PersonInformation(
                "name",
                "lastname",
                "location",
                "email"
        );
        Photographer photographer = Photographer.create(info);

        PersonInformation updatedInfo = new PersonInformation(
                "updatedName",
                "updatedLastname",
                "updatedLocation",
                "updatedEmail"
        );
        photographer.updatePersonInformation(updatedInfo);

        assertNotNull(photographer.getInformation());
        assertEquals(updatedInfo, photographer.getInformation());
    }

    @Test
    void photographerUpdatesPersonWithNullInformation(){
        PersonInformation info = new PersonInformation(
                "name",
                "lastname",
                "location",
                "email"
        );
        Photographer photographer = Photographer.create(info);

        PersonInformation updatedInfo = null;
        Exception exception = assertThrows(NullPointerException.class, () -> photographer.updatePersonInformation(updatedInfo));
        assertEquals("Person Information cannot be null", exception.getMessage());
    }

    @Test
    void photographerFreeBecomesPremium(){
        PersonInformation info = new PersonInformation(
                "name",
                "lastname",
                "location",
                "email"
        );
        Photographer photographer = Photographer.create(info);
        photographer.becomePremium();
        assertEquals(ContestLevel.PREMIUM, photographer.getLevel());
    }

    @Test
    void photographerPremiumBecomesPremiumException(){
        PersonInformation info = new PersonInformation(
                "name",
                "lastname",
                "location",
                "email"
        );
        Photographer photographer = Photographer.create(info);
        photographer.becomePremium();
        Exception e = assertThrows(IllegalStateException.class, photographer::becomePremium);
        assertEquals("Photographer " + photographer.getId() + " is already premium", e.getMessage());
    }

}