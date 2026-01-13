package com.application.picturecontest.infrastructure.in.web.controller;

import com.application.picturecontest.application.usecase.photographer.addphotographer.AddPhotographerUseCase;
import com.application.picturecontest.application.usecase.photographer.addphotographer.AddPhotographerInput;
import com.application.picturecontest.application.usecase.photographer.addphotographer.AddPhotographerOutput;
import com.application.picturecontest.application.usecase.photographer.becomepremium.BecomePremiumInput;
import com.application.picturecontest.application.usecase.photographer.becomepremium.BecomePremiumUseCase;
import com.application.picturecontest.application.usecase.photographer.findallphotographers.FindAllPhotographersOutput;
import com.application.picturecontest.application.usecase.photographer.findallphotographers.FindAllPhotographersUseCase;
import com.application.picturecontest.application.usecase.photographer.findphotographerbyid.FindPhotographerByIdInput;
import com.application.picturecontest.application.usecase.photographer.findphotographerbyid.FindPhotographerByIdOutput;
import com.application.picturecontest.application.usecase.photographer.findphotographerbyid.FindPhotographerByIdUseCase;
import com.application.picturecontest.infrastructure.in.web.dto.*;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/photographer")
public class PhotographerController {

    private final AddPhotographerUseCase addPhotographerUseCase;
    private final FindPhotographerByIdUseCase findPhotographerByIdUseCase;
    private final FindAllPhotographersUseCase findAllPhotographersUseCase;
    private final BecomePremiumUseCase becomePremiumUseCase;

    public PhotographerController(AddPhotographerUseCase addPhotographerUseCase, FindPhotographerByIdUseCase findPhotographerByIdUseCase, FindAllPhotographersUseCase findAllPhotographersUseCase, BecomePremiumUseCase becomePremiumUseCase) {
        this.addPhotographerUseCase = addPhotographerUseCase;
        this.findPhotographerByIdUseCase = findPhotographerByIdUseCase;
        this.findAllPhotographersUseCase = findAllPhotographersUseCase;
        this.becomePremiumUseCase = becomePremiumUseCase;
    }

    @PostMapping
    public ResponseEntity<SavePhotographerResponse> addPhotographer(@Valid @RequestBody AddPhotographerRequest request) {

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
    public ResponseEntity<FindPhotographerByIdResponse> getById(@PathVariable UUID id) {
        FindPhotographerByIdOutput result = findPhotographerByIdUseCase.execute(new FindPhotographerByIdInput(id));
        return ResponseEntity.ok(FindPhotographerByIdResponse.from(result.photographer()));
    }

    @GetMapping
    public ResponseEntity<FindAllPhotographersResponse> getAll() {
        FindAllPhotographersOutput result = findAllPhotographersUseCase.execute();
        return ResponseEntity.ok(new FindAllPhotographersResponse(result.photographers()));
    }

    @PostMapping("/{id}/premium")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void becomePremium(@PathVariable UUID id) {
        becomePremiumUseCase.execute(new BecomePremiumInput(id));
    }
}
