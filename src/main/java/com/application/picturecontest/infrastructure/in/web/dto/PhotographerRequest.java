package com.application.picturecontest.infrastructure.in.web.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record PhotographerRequest(

        @NotBlank(message = "name is required")
        @Size(max = 255, message = "name must be at most 255 characters")
        String name,

        @NotBlank(message = "lastname is required")
        @Size(max = 255, message = "lastname must be at most 255 characters")
        String lastname,

        @NotBlank(message = "location is required")
        @Size(max = 255, message = "location must be at most 255 characters")
        String location,

        @NotBlank(message = "email is required")
        @Size(max = 255, message = "email must be at most 255 characters")
        String email

) {
}


