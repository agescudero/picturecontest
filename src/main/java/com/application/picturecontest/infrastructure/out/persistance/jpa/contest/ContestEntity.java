package com.application.picturecontest.infrastructure.out.persistance.jpa.contest;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "contest")
public class ContestEntity {

    @Id
    @Column(name = "contest_id", nullable = false, updatable = false)
    private UUID id;

    @Embedded
    private ContestPriceEmbeddable price;

    @ElementCollection
    @CollectionTable(
            name = "contest_photographers",
            joinColumns = @JoinColumn(name = "contest_id")
    )
    @Column(name = "photographers", nullable = false)
    private Set<UUID> photographers = new HashSet<>();

    @ElementCollection
    @CollectionTable(
            name = "contest_categories",
            joinColumns = @JoinColumn(name = "contest_id")
    )
    @Column(name = "categories", nullable = false)
    private Set<UUID> categories = new HashSet<>();

//    @OneToMany(
//            mappedBy = "contest",
//            cascade = CascadeType.ALL,
//            orphanRemoval = true
//    )
//    private Set<ContestPhotoEntity> contestPhotos = new HashSet<>();

    protected ContestEntity() {
        // JPA
    }

    public ContestEntity(UUID id,
                         ContestPriceEmbeddable price,
                         Set<UUID> photographers,
                         Set<UUID> categories) {
        this.id = id;
        this.price = price;
        this.photographers = photographers;
        this.categories = categories;
    }

    public UUID getId() { return id; }

    public ContestPriceEmbeddable getPrice() { return price; }
    public void setPrice(ContestPriceEmbeddable price) { this.price = Objects.requireNonNull(price); }

    public Set<UUID> getPhotographers() {
        return photographers;
    }
    public void setPhotographers(Set<UUID> photographers) {
        this.photographers = photographers;
    }

    public Set<UUID> getCategories() {
        return categories;
    }
    public void setCategories(Set<UUID> categories) {
        this.categories = categories;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ContestEntity that)) return false;
        return id != null && id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return 31;
    }
}
