package com.application.picturecontest.infrastructure.out.persistance.jpa.photographer;

import com.application.picturecontest.domain.model.valueobject.ContestLevel;
import jakarta.persistence.*;

import java.util.Objects;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(
        name = "photographers",
        uniqueConstraints = {
                @UniqueConstraint(name = "uk_photographers_email", columnNames = "email")
        }
)
public class PhotographerEntity {

    @Id
    @Column(name = "id", nullable = false, updatable = false)
    private UUID id;

    @Column(name = "name", nullable = false, length = 255)
    private String name;

    @Column(name = "lastname", nullable = false, length = 255)
    private String lastname;

    @Column(name = "location", nullable = false, length = 255)
    private String location;

    @Column(name = "email", nullable = false, length = 255)
    private String email;

    @Enumerated(EnumType.STRING)
    @Column(name = "level", nullable = false, length = 16)
    private ContestLevel level;

    protected PhotographerEntity() {
        // JPA
    }

    public PhotographerEntity(UUID id,
                              String name,
                              String lastname,
                              String location,
                              String email,
                              ContestLevel level) {
        this.id = id;
        this.name = Objects.requireNonNull(name);
        this.lastname = Objects.requireNonNull(lastname);
        this.location = Objects.requireNonNull(location);
        this.email = Objects.requireNonNull(email);
        this.level = Objects.requireNonNull(level);
    }

    public UUID getId() { return id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = Objects.requireNonNull(name); }

    public String getLastname() { return lastname; }
    public void setLastname(String lastname) { this.lastname = Objects.requireNonNull(lastname); }

    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = Objects.requireNonNull(location); }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = Objects.requireNonNull(email); }

    public ContestLevel getLevel() { return level; }
    public void setLevel(ContestLevel level) { this.level = Objects.requireNonNull(level); }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PhotographerEntity that)) return false;
        return id != null && id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return 31;
    }
}
