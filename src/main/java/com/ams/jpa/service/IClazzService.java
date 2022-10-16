package com.ams.jpa.service;

import com.ams.jpa.model.dto.ClazzDto;
import com.ams.jpa.model.rest.request.CreateClazzRequest;
import lombok.NonNull;

import java.util.List;
import java.util.Optional;

public interface IClazzService {

    @NonNull List<ClazzDto> getAll();

    @NonNull Optional<ClazzDto> getById(@NonNull String id);

    @NonNull Optional<ClazzDto> getByName(@NonNull String name);

    @NonNull ClazzDto create(@NonNull CreateClazzRequest clazz);
}
