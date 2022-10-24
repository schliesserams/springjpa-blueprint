package com.ams.jpa.service;

import com.ams.jpa.model.dto.HeadTeacherDto;
import com.ams.jpa.model.rest.request.CreateHeadTeacherRequest;
import lombok.NonNull;

import java.util.List;
import java.util.Optional;

public interface IHeadTeacherService {
    @NonNull List<HeadTeacherDto> getAll();

    @NonNull Optional<HeadTeacherDto> getById(@NonNull String id);

    @NonNull Optional<HeadTeacherDto> getByName(@NonNull String name);

    @NonNull HeadTeacherDto create(@NonNull CreateHeadTeacherRequest headTeacher);
}
