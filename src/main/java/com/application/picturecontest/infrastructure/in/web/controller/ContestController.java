package com.application.picturecontest.infrastructure.in.web.controller;

import com.application.picturecontest.application.usecase.contest.addContest.AddContestInput;
import com.application.picturecontest.application.usecase.contest.addContest.AddContestOutput;
import com.application.picturecontest.application.usecase.contest.addContest.AddContestUseCase;
import com.application.picturecontest.domain.model.valueobject.ContestPrice;
import com.application.picturecontest.infrastructure.in.web.dto.contest.AddContestRequest;
import com.application.picturecontest.infrastructure.in.web.dto.contest.AddContestResponse;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/contest")
public class ContestController {

    private final AddContestUseCase addContestUseCase;

    public ContestController(AddContestUseCase addContestUseCase) {
        this.addContestUseCase = addContestUseCase;
    }

    @PostMapping
    public ResponseEntity<AddContestResponse> addCategory(@Valid @RequestBody AddContestRequest request) {
        ContestPrice price = new ContestPrice(request.amount(), request.currency());
        AddContestOutput output = addContestUseCase.execute(
                new AddContestInput(price)
        );

        return ResponseEntity.ok(new AddContestResponse(output.id()));
    }
//
//    @GetMapping("/{id}")
//    public ResponseEntity<FindCategoryByIdResponse> getById(@PathVariable UUID id) {
//        FindCategoryByIdOutput result = findCategoryByIdUseCase.execute(new FindCategoryByIdInput(id));
//        return ResponseEntity.ok(FindCategoryByIdResponse.from(result.category()));
//    }
//
//    @GetMapping
//    public ResponseEntity<FindAllCategoriesResponse> getAll() {
//        FindAllCategoriesOutput result = findAllCategoriesUseCase.execute();
//        return ResponseEntity.ok(new FindAllCategoriesResponse(result.categories()));
//    }

}
