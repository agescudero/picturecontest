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

    private final Set<Photographer> photographers = new HashSet<>();//Set<Participation>
    private final Set<UUID> juryMembers = new HashSet<>();
    private final Set<Category> categories = new HashSet<>();
    private final Set<UUID> phases = new HashSet<>();
    private final Set<ContestPhoto> contestPhotos = new HashSet<>();
    private final Set<ContestPhoto> oldContestPhotos = new HashSet<>();

    private Contest(UUID id, ContestPrice price) {
        this.id = Objects.requireNonNull(id, "contestId cannot be null");
        this.price = price;
    }

    public static Contest create(ContestPrice price){
        return new Contest(UUID.randomUUID(), price);
    }

    public Photographer addPhotographer(PersonInformation info) {
        Objects.requireNonNull(info, "Person information cannot be null");
        Photographer photographer = Photographer.create(info);
        if (!this.photographers.add(photographer)) {
            throw new IllegalStateException("Photographer cannot be duplicated");
        }
        return photographer;
    }

    public void addJuryMember(UUID juryId) {
        Objects.requireNonNull(juryId, "juryMemberId cannot be null");
        if (!this.juryMembers.add(juryId)) {
            throw new IllegalStateException("juryMemberId cannot be duplicated");
        }
    }

    public Category addCategory(String name, String description, ContestLevel level) {
        validateNotNull(name, "Name cannot be null");
        validateNotNull(description, "Description cannot be null");
        validateNotNull(level, "Contest level cannot be null");
        Category category = Category.create(name, description, level);

        if (categories.stream().anyMatch(c -> name.equals(c.getName()))
        ) {
            throw new IllegalStateException("Category with name " + name + " already exist");
        }

        if (!this.categories.add(category)) {
            throw new IllegalStateException("Category cannot be duplicated");
        }

        return category;
    }

    public void addPhase(UUID phaseId) {
        Objects.requireNonNull(phaseId, "phaseId cannot be null");
        if (!this.phases.add(phaseId)) {
            throw new IllegalStateException("phaseId cannot be duplicated");
        }
    }

    public ContestPhoto submitPhoto(UUID photographerId, UUID categoryId) {

        Photographer photographer = findPhotographerOrThrow(photographerId);
        Category category = findCategoryOrThrow(categoryId);

        ensurePhotographerCanSubmitToCategory(photographer, category);

        ContestPhoto contestPhoto = new ContestPhoto(photographerId, categoryId);
        contestPhotos.add(contestPhoto);

        return contestPhoto;
    }

    public void deletePhoto(UUID photoId){}

    private Photographer findPhotographerOrThrow(UUID photographerId) {
        return photographers.stream()
                .filter(p -> p.getId().equals(photographerId))
                .findFirst()
                .orElseThrow(() ->
                        new IllegalArgumentException("Photographer not valid"));
    }

    private Category findCategoryOrThrow(UUID categoryId) {
        return categories.stream()
                .filter(c -> c.getId().equals(categoryId))
                .findFirst()
                .orElseThrow(() ->
                        new IllegalArgumentException("Category doesn't exist"));
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

    private static void validateNotNull(Object value, String message) {
        if (value == null) {
            throw new IllegalArgumentException(message);
        }
    }

    public Set<Photographer> getPhotographers() {
        return photographers;
    }

    public Set<UUID> getJuryMembers() {
        return juryMembers;
    }

    public Set<Category> getCategories() {
        return categories;
    }

    public Set<UUID> getPhases() {
        return phases;
    }

    public Set<ContestPhoto> getContestPhotos() {
        return contestPhotos;
    }
}
