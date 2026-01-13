package com.application.picturecontest.infrastructure.out.persistance.jpa.category;

import com.application.picturecontest.domain.model.valueobject.ContestLevel;
import jakarta.persistence.*;

import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "categories")
public class CategoryEntity {

    @Id
    @Column(name = "id", nullable = false, updatable = false)
    private UUID id;

    @Column(name = "name", nullable = false, length = 255)
    private String name;

    @Column(name = "description", nullable = false, length = 255)
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(name = "level", nullable = false, length = 16)
    private ContestLevel level;

    protected CategoryEntity() {
        // JPA
    }

    public CategoryEntity(UUID id,
                          String name,
                          String description,
                          ContestLevel level) {
        this.id = id;
        this.name = Objects.requireNonNull(name);
        this.description = Objects.requireNonNull(description);
        this.level = Objects.requireNonNull(level);
    }

    public UUID getId() { return id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = Objects.requireNonNull(name); }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = Objects.requireNonNull(description); }

    public ContestLevel getLevel() { return level; }
    public void setLevel(ContestLevel level) { this.level = Objects.requireNonNull(level); }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CategoryEntity that)) return false;
        return id != null && id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return 31;
    }
}
