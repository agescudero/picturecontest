package com.application.picturecontest.domain.model.contest;

import com.application.picturecontest.domain.model.participant.Photographer;
import com.application.picturecontest.domain.model.valueobject.ContestLevel;
import com.application.picturecontest.domain.model.valueobject.PersonInformation;
import org.jspecify.annotations.NonNull;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.UUID;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class ContestTest {

    @Test
    void addPhotographer_ok() {
        Contest contest = Contest.create(null);
        Photographer photographer = Photographer.create(getInfo());
        contest.addPhotographer(photographer.getInformation());
        assertTrue(contest.getPhotographers().contains(photographer));
    }

    @Test
    void addPhotographer_nullException(){
        Contest contest = Contest.create(null);
        Exception exception = assertThrows(NullPointerException.class,
                () -> contest.addPhotographer(null));
        assertEquals("Person information cannot be null", exception.getMessage());
    }

    @Test
    void addPhotographer_idDuplicatedException() {
        PersonInformation info = getInfo();
        Contest contest = Contest.create(null);
        contest.addPhotographer(info);
        Exception exception = assertThrows(IllegalStateException.class,
                () -> contest.addPhotographer(info));
        assertEquals("Photographer cannot be duplicated", exception.getMessage());
    }

    @Test
    void addJuryMember_ok() {
        UUID juryId = UUID.randomUUID();
        Contest contest = Contest.create(null);
        contest.addJuryMember(juryId);
        assertTrue(contest.getJuryMembers().contains(juryId));
    }

    @Test
    void addJuryMember_nullIdException(){
        Contest contest = Contest.create(null);
        Exception exception = assertThrows(NullPointerException.class,
                () -> contest.addJuryMember(null));
        assertEquals("juryMemberId cannot be null", exception.getMessage());
    }

    @Test
    void addJuryMember_idDuplicatedException() {
        UUID juryId = UUID.randomUUID();
        Contest contest = Contest.create(null);
        contest.addJuryMember(juryId);
        Exception exception = assertThrows(IllegalStateException.class,
                () -> contest.addJuryMember(juryId));
        assertEquals("juryMemberId cannot be duplicated", exception.getMessage());
    }

    @Test
    void addCategory_ok() {
        Contest contest = Contest.create(null);
        Category expected = contest.addCategory("name", "description", ContestLevel.FREE);
        assertTrue(contest.getCategories().contains(expected));
    }

    @ParameterizedTest
    @MethodSource("nullCategoryArguments")
    void addCategory_nullArguments_throwException(
            String name,
            String description,
            ContestLevel level,
            String expectedMessage
    ) {
        Contest contest = Contest.create(null);

        Exception exception = assertThrows(
                IllegalArgumentException.class,
                () -> contest.addCategory(name, description, level)
        );

        assertEquals(expectedMessage, exception.getMessage());
    }

    private static Stream<Arguments> nullCategoryArguments() {
        return Stream.of(
                Arguments.of(null, "desc", ContestLevel.FREE, "Name cannot be null"),
                Arguments.of("name", null, ContestLevel.FREE, "Description cannot be null"),
                Arguments.of("name", "desc", null, "Contest level cannot be null")
        );
    }


    @Test
    void addCategory_idDuplicatedException() {
        Contest contest = Contest.create(null);
        String name = "name";
        String description = "description";
        Category category = contest.addCategory(name ,description, ContestLevel.FREE);
        Exception exception = assertThrows(IllegalStateException.class,
                () -> contest.addCategory(name, category.getDescription(), ContestLevel.FREE));
        assertEquals("Category with name " + name + " already exist", exception.getMessage());
    }

    @Test
    void addPhase_ok() {
        UUID phaseId = UUID.randomUUID();
        Contest contest = Contest.create(null);
        contest.addPhase(phaseId);
        assertTrue(contest.getPhases().contains(phaseId));
    }

    @Test
    void addPhase_nullIdException(){
        Contest contest = Contest.create(null);
        Exception exception = assertThrows(NullPointerException.class,
                () -> contest.addPhase(null));
        assertEquals("phaseId cannot be null", exception.getMessage());
    }

    @Test
    void addPhase_idDuplicatedException() {
        UUID phaseId = UUID.randomUUID();
        Contest contest = Contest.create(null);
        contest.addPhase(phaseId);
        Exception exception = assertThrows(IllegalStateException.class,
                () -> contest.addPhase(phaseId));
        assertEquals("phaseId cannot be duplicated", exception.getMessage());
    }

    @Test
    void photographerNotPremiumSubmitsPhotoToFreeCategory(){
        Contest contest = Contest.create(null);

        Photographer photographer = contest.addPhotographer(getInfo());
        ContestLevel level = ContestLevel.FREE;
        Category category = contest.addCategory("categoryName", "categoryDescription", level);

        ContestPhoto expected = contest.submitPhoto(photographer.getId(),category.getId());

        assertEquals(1, contest.getContestPhotos().size());
        assertTrue(contest.getContestPhotos().contains(expected));
    }

    @Test
    void photographerNotPremiumSubmitsPhotoToPremiumCategoryException(){
        Contest contest = Contest.create(null);

        Photographer photographer = contest.addPhotographer(getInfo());
        ContestLevel level = ContestLevel.PREMIUM;
        Category category = contest.addCategory("categoryName", "categoryDescription", level);

        Exception e = assertThrows(
                IllegalStateException.class, () -> contest.submitPhoto(photographer.getId(), category.getId())
        );
        assertEquals(
                "Photographer with id " + photographer.getId() + " must be " + category.getLevel(),
                e.getMessage()
        );
    }

    @Test
    void photographerPremiumSubmitsPhotoToFreeCategory(){
        Contest contest = Contest.create(null);

        Photographer photographer = contest.addPhotographer(getInfo());
        photographer.becomePremium();
        ContestLevel level = ContestLevel.FREE;
        Category category = contest.addCategory("categoryName", "categoryDescription", level);

        ContestPhoto expected = contest.submitPhoto(photographer.getId(),category.getId());

        assertEquals(1, contest.getContestPhotos().size());
        assertTrue(contest.getContestPhotos().contains(expected));
    }

    @Test
    void photographerSubmitsPhotoToNotValidCategory(){
        Contest contest = Contest.create(null);
        UUID categoryId = UUID.randomUUID();

        Photographer photographer = contest.addPhotographer(getInfo());

        Exception exception = assertThrows(IllegalArgumentException.class,
                () -> contest.submitPhoto(photographer.getId(), categoryId));
        assertEquals("Category doesn't exist", exception.getMessage());
    }

    @Test
    void notRegisteredPhotographerSubmitsPhotoCategory(){
        Contest contest = Contest.create(null);

        UUID photographerId = UUID.randomUUID();
        ContestLevel level = ContestLevel.FREE;
        Category category = contest.addCategory("categoryName", "categoryDescription", level);

        Exception exception = assertThrows(IllegalArgumentException.class,
                () -> contest.submitPhoto(photographerId, category.getId()));
        assertEquals("Photographer not valid", exception.getMessage());
    }



    private static PersonInformation getInfo() {
        return new PersonInformation(
                "name",
                "lastname",
                "location",
                "email"
        );
    }

}