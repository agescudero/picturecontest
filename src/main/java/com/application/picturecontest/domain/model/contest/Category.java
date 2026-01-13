package com.application.picturecontest.domain.model.contest;

import com.application.picturecontest.domain.model.valueobject.ContestLevel;

import java.util.Objects;
import java.util.UUID;

public class Category {

    private final UUID id;
    private String name;
    private String description;
    private ContestLevel level;


    private Category(UUID id, String name, String description, ContestLevel level) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.level = level;
    }

    public static Category create(String name, String description, ContestLevel level) {
        return new Category(UUID.randomUUID(), name, description, level);
    }

    public static Category of(UUID id, String name, String description, ContestLevel level) {
        return new Category(id, name, description, level);
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

    public ContestLevel getLevel() {
        return level;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Category category)) return false;
        return Objects.equals(name, category.name) &&
                Objects.equals(description, category.description) &&
                level == category.level;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, description, level);
    }
}
