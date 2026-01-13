package com.application.picturecontest.infrastructure.in.web.dto.category;

import com.application.picturecontest.domain.model.valueobject.ContestLevel;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record AddCategoryRequest(

        @NotBlank(message = "name is required")
        @Size(max = 255, message = "name must be at most 255 characters")
        String name,

        @NotBlank(message = "description is required")
        @Size(max = 255, message = "description must be at most 255 characters")
        String description,

        @NotNull
        ContestLevel level

) {
}


