package com.application.picturecontest.domain.model.contest;

import com.application.picturecontest.domain.model.participant.Photographer;
import com.application.picturecontest.domain.model.valueobject.ContestLevel;
import com.application.picturecontest.domain.model.valueobject.ContestPrice;
import com.application.picturecontest.domain.model.valueobject.PersonInformation;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;

public class Contest {

    private UUID id;
    private ContestPrice price;

    private final Set<UUID> photographers = new HashSet<>();//Set<Participation>
    private final Set<UUID> juryMembers = new HashSet<>();
    private final Set<UUID> categories = new HashSet<>();
    private final Set<UUID> phases = new HashSet<>();
    private final Set<ContestPhoto> contestPhotos = new HashSet<>();
    private final Set<ContestPhoto> oldContestPhotos = new HashSet<>();

    private Contest(UUID id, ContestPrice price) {
        this.id = Objects.requireNonNull(id, "contestId cannot be null");
        this.price = price;
    }

    private Contest(UUID id,
                    ContestPrice price,
                    Set<UUID> photographers,
                    Set<UUID> categories
    ) {
        this.id = Objects.requireNonNull(id, "contestId cannot be null");
        this.price = price;
    }

    public static Contest create(ContestPrice price) {
        return new Contest(UUID.randomUUID(), price);
    }

    public static Contest of(UUID id,
                             ContestPrice price,
                             Set<UUID> photographers,
                             Set<UUID> categories
    ) {
        return new Contest(id, price, photographers, categories);
    }

    public void addPhotographerToContest(UUID id) {
        Objects.requireNonNull(id, "PhotographerId cannot be null");
        if (!this.photographers.add(id)) {
            throw new IllegalStateException("Photographer cannot be duplicated");
        }
    }

    public void addJuryMember(UUID juryId) {
        Objects.requireNonNull(juryId, "juryMemberId cannot be null");
        if (!this.juryMembers.add(juryId)) {
            throw new IllegalStateException("juryMemberId cannot be duplicated");
        }
    }

//    public void addCategory(String name, String description, ContestLevel level) {
//        validateNotNull(name, "Name cannot be null");
//        validateNotNull(description, "Description cannot be null");
//        validateNotNull(level, "Contest level cannot be null");
//        Category category = Category.create(name, description, level);
//
//        if (categories.stream().anyMatch(c -> name.equals(c.getName()))
//        ) {
//            throw new IllegalStateException("Category with name " + name + " already exist");
//        }
//
//        if (!this.categories.add(category)) {
//            throw new IllegalStateException("Category cannot be duplicated");
//        }
//
//        return category;
//    }

    public void addCategoryToContest(UUID categoryId) {
        Objects.requireNonNull(categoryId, "CategoryId cannot be null");
        if (!this.categories.add(categoryId)) {
            throw new IllegalStateException("Category cannot be duplicated");
        }
    }

    public void addPhase(UUID phaseId) {
        Objects.requireNonNull(phaseId, "phaseId cannot be null");
        if (!this.phases.add(phaseId)) {
            throw new IllegalStateException("phaseId cannot be duplicated");
        }
    }

    public ContestPhoto submitPhoto(Photographer photographer, Category category) {

        if (!photographers.contains(photographer.getId())) throw new IllegalArgumentException("Photographer not valid");
        if (!categories.contains(category.getId())) throw new IllegalArgumentException("Category not valid");

        ensurePhotographerCanSubmitToCategory(photographer, category);

        ContestPhoto contestPhoto = new ContestPhoto(photographer.getId(), category.getId());
        contestPhotos.add(contestPhoto);

        return contestPhoto;
    }

    private void ensurePhotographerCanSubmitToCategory(
            Photographer photographer,
            Category category
    ) {
        if (!photographer.getLevel().canSubmitTo(category.getLevel())) {
            throw new IllegalStateException(
                    "Photographer with id " + photographer.getId() + " must be " + category.getLevel()
            );
        }
    }

    public void deletePhoto(UUID photoId) {
    }


    private static void validateNotNull(Object value, String message) {
        if (value == null) {
            throw new IllegalArgumentException(message);
        }
    }

    public UUID getId() {
        return id;
    }

    public ContestPrice getPrice() {
        return price;
    }

    public Set<UUID> getPhotographers() {
        return photographers;
    }

    public Set<UUID> getJuryMembers() {
        return juryMembers;
    }

    public Set<UUID> getCategories() {
        return categories;
    }

    public Set<UUID> getPhases() {
        return phases;
    }

    public Set<ContestPhoto> getContestPhotos() {
        return contestPhotos;
    }
}
