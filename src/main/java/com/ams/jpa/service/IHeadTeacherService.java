package com.ams.jpa.service;

import com.ams.jpa.model.dto.HeadTeacherDto;
import com.ams.jpa.model.rest.request.CreateHeadTeacherRequest;
import lombok.NonNull;

import java.util.List;
import java.util.Optional;

public interface IHeadTeacherService {
    @NonNull List<HeadTeacherDto> findAll();
    @NonNull Optional<HeadTeacherDto> findById(@NonNull String id);
    @NonNull Optional<HeadTeacherDto> findByName(@NonNull String name);
    @NonNull HeadTeacherDto create(@NonNull CreateHeadTeacherRequest headTeacher);
}
