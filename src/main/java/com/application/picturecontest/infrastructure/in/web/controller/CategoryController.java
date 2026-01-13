package com.application.picturecontest.infrastructure.in.web.controller;

import com.application.picturecontest.application.usecase.category.addcategory.AddCategoryInput;
import com.application.picturecontest.application.usecase.category.addcategory.AddCategoryOutput;
import com.application.picturecontest.application.usecase.category.addcategory.AddCategoryUseCase;
import com.application.picturecontest.application.usecase.category.findallcategories.FindAllCategoriesOutput;
import com.application.picturecontest.application.usecase.category.findallcategories.FindAllCategoriesUseCase;
import com.application.picturecontest.application.usecase.category.findcategorybyid.FindCategoryByIdInput;
import com.application.picturecontest.application.usecase.category.findcategorybyid.FindCategoryByIdOutput;
import com.application.picturecontest.application.usecase.category.findcategorybyid.FindCategoryByIdUseCase;
import com.application.picturecontest.infrastructure.in.web.dto.category.AddCategoryRequest;
import com.application.picturecontest.infrastructure.in.web.dto.category.AddCategoryResponse;
import com.application.picturecontest.infrastructure.in.web.dto.category.FindAllCategoriesResponse;
import com.application.picturecontest.infrastructure.in.web.dto.category.FindCategoryByIdResponse;
import com.application.picturecontest.infrastructure.in.web.dto.photographer.FindAllPhotographersResponse;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/category")
public class CategoryController {

    private final AddCategoryUseCase addCategoryUseCase;
    private final FindCategoryByIdUseCase findCategoryByIdUseCase;
    private final FindAllCategoriesUseCase findAllCategoriesUseCase;

    public CategoryController(AddCategoryUseCase addCategoryUseCase, FindCategoryByIdUseCase findCategoryByIdUseCase, FindAllCategoriesUseCase findAllCategoriesUseCase) {
        this.addCategoryUseCase = addCategoryUseCase;
        this.findCategoryByIdUseCase = findCategoryByIdUseCase;
        this.findAllCategoriesUseCase = findAllCategoriesUseCase;
    }

    @PostMapping
    public ResponseEntity<AddCategoryResponse> addCategory(@Valid @RequestBody AddCategoryRequest request) {

        AddCategoryOutput output = addCategoryUseCase.execute(
                new AddCategoryInput(
                        request.name(),
                        request.description(),
                        request.level()
                )
        );

        return ResponseEntity.ok(new AddCategoryResponse(output.id()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<FindCategoryByIdResponse> getById(@PathVariable UUID id) {
        FindCategoryByIdOutput result = findCategoryByIdUseCase.execute(new FindCategoryByIdInput(id));
        return ResponseEntity.ok(FindCategoryByIdResponse.from(result.category()));
    }

    @GetMapping
    public ResponseEntity<FindAllCategoriesResponse> getAll() {
        FindAllCategoriesOutput result = findAllCategoriesUseCase.execute();
        return ResponseEntity.ok(new FindAllCategoriesResponse(result.categories()));
    }

}
