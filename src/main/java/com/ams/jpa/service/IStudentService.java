package com.ams.jpa.service;

import com.ams.jpa.model.dto.GradeDto;
import com.ams.jpa.model.dto.StudentDto;
import com.ams.jpa.model.rest.request.CreateStudentRequest;
import lombok.NonNull;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface IStudentService {
    @NonNull List<StudentDto> findAll();

    @NonNull StudentDto create(@NonNull CreateStudentRequest createStudent);

    @NonNull Optional<StudentDto> findById(@NonNull String id);

    @NonNull StudentDto addGrade(@NonNull String id, @NonNull String clazzId, @NonNull BigDecimal grade);

    @NonNull List<GradeDto> getGrades(@NonNull String id);
}
