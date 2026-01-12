package com.application.picturecontest.application.usecase.findallphotographers;

import com.application.picturecontest.domain.model.participant.Photographer;

import java.util.List;

public record FindAllPhotographersOutput(List<Photographer> photographers) {
}
