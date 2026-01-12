package com.application.picturecontest.domain.model.contest;

import java.util.Objects;
import java.util.UUID;

public class ContestPhoto {

    private final UUID id;
    private final UUID photographerId;
    private final UUID categoryId;
    private String name;
    private String description;

    public ContestPhoto(UUID photographerId, UUID categoryId) {
        this.id = UUID.randomUUID();
        this.photographerId = photographerId;
        this.categoryId = categoryId;
    }

    public UUID getPhotographerId() {
        return photographerId;
    }

    public UUID getCategoryId() {
        return categoryId;
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof ContestPhoto that)) return false;
        return Objects.equals(photographerId, that.photographerId)
                && Objects.equals(categoryId, that.categoryId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(photographerId, categoryId);
    }
}