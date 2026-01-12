package com.application.picturecontest.infrastructure.in.web.controller;

import com.application.picturecontest.application.usecase.addphotographer.AddPhotographerUseCase;
import com.application.picturecontest.application.usecase.addphotographer.AddPhotographerInput;
import com.application.picturecontest.application.usecase.addphotographer.AddPhotographerOutput;
import com.application.picturecontest.application.usecase.findphotographerbyid.FindPhotographerByIdInput;
import com.application.picturecontest.application.usecase.findphotographerbyid.FindPhotographerByIdOutput;
import com.application.picturecontest.application.usecase.findphotographerbyid.FindPhotographerByIdUseCase;
import com.application.picturecontest.infrastructure.in.web.dto.FindPhotographerByIdResponse;
import com.application.picturecontest.infrastructure.in.web.dto.PhotographerRequest;
import com.application.picturecontest.infrastructure.in.web.dto.SavePhotographerResponse;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/photographer")
public class PhotographerController {

    private final AddPhotographerUseCase addPhotographerUseCase;
    private final FindPhotographerByIdUseCase findPhotographerByIdUseCase;

    public PhotographerController(AddPhotographerUseCase addPhotographerUseCase, FindPhotographerByIdUseCase findPhotographerByIdUseCase) {
        this.addPhotographerUseCase = addPhotographerUseCase;
        this.findPhotographerByIdUseCase = findPhotographerByIdUseCase;
    }

    @PostMapping
    public ResponseEntity<SavePhotographerResponse> addPhotographer(@Valid @RequestBody PhotographerRequest request) {

        AddPhotographerOutput output = addPhotographerUseCase.execute(
                new AddPhotographerInput(
                        request.name(),
                        request.lastname(),
                        request.location(),
                        request.email()
                )
        );

        return ResponseEntity.ok(new SavePhotographerResponse(output.id()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<FindPhotographerByIdResponse> getById(@PathVariable UUID id){
        FindPhotographerByIdOutput result = findPhotographerByIdUseCase.execute(new FindPhotographerByIdInput(id));
        return ResponseEntity.ok(FindPhotographerByIdResponse.from(result.photographer()));
    }
}
