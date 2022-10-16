package com.ams.jpa.service;

import com.ams.jpa.model.entity.Pupil;
import com.ams.jpa.model.rest.request.CreatePupilDto;
import lombok.NonNull;

import java.util.List;
import java.util.Optional;

public interface IPupilService {
    @NonNull List<Pupil> findAll();
    @NonNull Pupil create(@NonNull CreatePupilDto createPupil);

    @NonNull Optional<Pupil> findById(@NonNull String id);
    @NonNull Optional<Pupil> findByLastname(@NonNull String lastname);
}
