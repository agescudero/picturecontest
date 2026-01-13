package com.application.picturecontest.application.usecase.contest.addContest;

import com.application.picturecontest.domain.model.contest.Contest;
import com.application.picturecontest.domain.port.ContestRepository;

import java.util.UUID;

public class AddContestUseCase {

    private final ContestRepository repository;

    public AddContestUseCase(ContestRepository repository) {
        this.repository = repository;
    }

    public AddContestOutput execute(AddContestInput input) {
        UUID savedId = repository.save(Contest.create(input.price()));
        return new AddContestOutput(savedId);
    }
}
