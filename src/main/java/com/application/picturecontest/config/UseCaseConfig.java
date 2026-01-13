package com.application.picturecontest.config;

import com.application.picturecontest.application.usecase.photographer.addphotographer.AddPhotographerUseCase;
import com.application.picturecontest.application.usecase.photographer.becomepremium.BecomePremiumUseCase;
import com.application.picturecontest.application.usecase.photographer.findallphotographers.FindAllPhotographersUseCase;
import com.application.picturecontest.application.usecase.photographer.findphotographerbyid.FindPhotographerByIdUseCase;
import com.application.picturecontest.domain.port.PhotographerRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UseCaseConfig {

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

}
