package com.application.picturecontest.infrastructure.in.web.dto;

import com.application.picturecontest.domain.model.participant.Photographer;

import java.util.List;

public record FindAllPhotographersResponse(List<Photographer> photographers) {
}
