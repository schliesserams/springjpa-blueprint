package com.ams.jpa.service;

import com.ams.jpa.model.entity.HeadTeacher;
import com.ams.jpa.model.rest.request.CreateHeadTeacherDto;
import lombok.NonNull;

import java.util.List;
import java.util.Optional;

public interface IHeadTeacherService {
    @NonNull List<HeadTeacher> findAll();
    @NonNull Optional<HeadTeacher> findById(@NonNull String id);
    @NonNull Optional<HeadTeacher> findByName(@NonNull String name);
    @NonNull HeadTeacher create(@NonNull CreateHeadTeacherDto createHeadTeacher);
}
