package com.application.picturecontest.config;

import com.application.picturecontest.application.usecase.addphotographer.AddPhotographerUseCase;
import com.application.picturecontest.application.usecase.findphotographerbyid.FindPhotographerByIdUseCase;
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

}
