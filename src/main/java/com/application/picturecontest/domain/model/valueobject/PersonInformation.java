package com.application.picturecontest.domain.model.valueobject;

import java.util.Objects;

public class PersonInformation {

    private final String name;
    private final String lastname;
    private final String location;
    private final String email;


    public PersonInformation(String name, String lastname, String location, String email) {
        this.name = name;
        this.lastname = lastname;
        this.location = location;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public String getLastname() {
        return lastname;
    }

    public String getLocation() {
        return location;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof PersonInformation that)) return false;
        return Objects.equals(name, that.name) &&
                Objects.equals(lastname, that.lastname) &&
                Objects.equals(location, that.location) &&
                Objects.equals(email, that.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, lastname, location, email);
    }
}
