package com.application.picturecontest.config;

import com.application.picturecontest.application.usecase.category.addcategory.AddCategoryUseCase;
import com.application.picturecontest.application.usecase.category.findallcategories.FindAllCategoriesUseCase;
import com.application.picturecontest.application.usecase.category.findcategorybyid.FindCategoryByIdUseCase;
import com.application.picturecontest.application.usecase.contest.addContest.AddContestUseCase;
import com.application.picturecontest.application.usecase.photographer.addphotographer.AddPhotographerUseCase;
import com.application.picturecontest.application.usecase.photographer.becomepremium.BecomePremiumUseCase;
import com.application.picturecontest.application.usecase.photographer.findallphotographers.FindAllPhotographersUseCase;
import com.application.picturecontest.application.usecase.photographer.findphotographerbyid.FindPhotographerByIdUseCase;
import com.application.picturecontest.domain.port.CategoryRepository;
import com.application.picturecontest.domain.port.ContestRepository;
import com.application.picturecontest.domain.port.PhotographerRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UseCaseConfig {

    //***********CONTEST**************

    @Bean
    public AddContestUseCase addContestUseCase(
            ContestRepository repository
    ) {
        return new AddContestUseCase(repository);
    }


    //***********PHOTOGRAPHER**************

    @Bean
    public AddPhotographerUseCase addPhotographerUseCase(
            PhotographerRepository repository
    ) {
        return new AddPhotographerUseCase(repository);
    }

    @Bean
    public FindPhotographerByIdUseCase findPhotographerByIdUseCase(
            PhotographerRepository repository
    ) {
        return new FindPhotographerByIdUseCase(repository);
    }

    @Bean
    public BecomePremiumUseCase becomePremiumUseCase(
            PhotographerRepository repository
    ) {
        return new BecomePremiumUseCase(repository);
    }

    @Bean
    public FindAllPhotographersUseCase findAllPhotographersUseCase(
            PhotographerRepository repository
    ) {
        return new FindAllPhotographersUseCase(repository);
    }

    //***********CATEGORY**************

    @Bean
    public AddCategoryUseCase addCategoryUseCase(
            CategoryRepository repository
    ) {
        return new AddCategoryUseCase(repository);
    }

    @Bean
    public FindCategoryByIdUseCase findCategoryByIdUseCase(
            CategoryRepository repository
    ) {
        return new FindCategoryByIdUseCase(repository);
    }

    @Bean
    public FindAllCategoriesUseCase findAllCategoriesUseCase(
            CategoryRepository repository
    ) {
        return new FindAllCategoriesUseCase(repository);
    }

}
