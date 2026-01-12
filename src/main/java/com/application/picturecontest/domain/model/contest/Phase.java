package com.application.picturecontest.domain.model.contest;

import java.util.UUID;

public abstract class  Phase {

    private UUID id;
    private String name;

    public abstract void vote();
}
