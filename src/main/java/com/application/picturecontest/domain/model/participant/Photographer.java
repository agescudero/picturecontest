package com.application.picturecontest.domain.model.participant;

import com.application.picturecontest.domain.model.valueobject.ContestLevel;
import com.application.picturecontest.domain.model.valueobject.PersonInformation;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;

public class Photographer {

    private final UUID id;
    private PersonInformation information;
    private final Set<UUID> photos;
    private ContestLevel level;


    private Photographer(UUID id, PersonInformation information, Set<UUID> photos, ContestLevel level) {
        this.id = id;
        this.information = information;
        this.photos = photos;
        this.level = level;
    }

    public static Photographer create(PersonInformation info) {
        Objects.requireNonNull(info, "Person Information cannot be null");
        return new Photographer(UUID.randomUUID(), info, new HashSet<>(), ContestLevel.FREE);
    }

    public static Photographer of(UUID id, PersonInformation info, Set<UUID> photos, ContestLevel level){
        return new Photographer(id, info, photos, level);
    }

    public void updatePersonInformation(PersonInformation updatedInfo){
        Objects.requireNonNull(updatedInfo, "Person Information cannot be null");
        this.information = updatedInfo;
    }

    public void becomePremium(){
        if(ContestLevel.PREMIUM.equals(this.level)){
            throw new IllegalStateException("Photographer " + this.id + " is already premium");
        }
        this.level = ContestLevel.PREMIUM;
    }

    public UUID getId() {
        return id;
    }

    public PersonInformation getInformation() {
        return information;
    }

    public Set<UUID> getPhotos() {
        return photos;
    }

    public ContestLevel getLevel() {
        return level;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Photographer that)) return false;
        return Objects.equals(information, that.information) || Objects.equals(id, that.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(information.getEmail());
    }
}
