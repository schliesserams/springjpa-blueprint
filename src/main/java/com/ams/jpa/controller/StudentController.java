package com.ams.jpa.controller;

import com.ams.jpa.component.mapper.GradeMapper;
import com.ams.jpa.component.mapper.StudentMapper;
import com.ams.jpa.model.rest.request.CreateStudentRequest;
import com.ams.jpa.model.rest.response.GradeResponse;
import com.ams.jpa.model.rest.response.StudentResponse;
import com.ams.jpa.service.IStudentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.math.BigDecimal;
import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;


@RestController
@RequestMapping("/v1")
@RequiredArgsConstructor
@Tag(name = "Students", description = "Handles the students in school")
@Slf4j
@Validated
public class StudentController {
    private final IStudentService studentService;
    private final StudentMapper studentMapper;
    private final GradeMapper gradeMapper;

    @Operation(summary = "Creates a student")
    @ApiResponse(responseCode = "200", description = "The created student")
    @ApiResponse(responseCode = "404", description = "The head teacher was not found")
    @ApiResponse(responseCode = "500", description = "A server error")
    @PostMapping(value = "/student", produces = APPLICATION_JSON_VALUE)
    public @NonNull ResponseEntity<StudentResponse> create(@NonNull @RequestBody @Valid CreateStudentRequest createStudent) {
        return ResponseEntity.ok(studentMapper.asResponse(studentService.create(createStudent)));
    }

    @Operation(summary = "Gets a student")
    @ApiResponse(responseCode = "200", description = "The created student")
    @ApiResponse(responseCode = "404", description = "The student was not found")
    @ApiResponse(responseCode = "500", description = "A server error")
    @GetMapping(value = "/student/{id}", produces = APPLICATION_JSON_VALUE)
    public @NonNull ResponseEntity<StudentResponse> findById(@NonNull @PathVariable String id) {
        return ResponseEntity.ok(studentMapper.asResponse(studentService.findById(id)
                .orElseThrow(EntityNotFoundException::new)));
    }

    @Operation(summary = "Add a grade for a student")
    @ApiResponse(responseCode = "202", description = "The grade was added")
    @ApiResponse(responseCode = "404", description = "The student or clazz  was not found")
    @ApiResponse(responseCode = "500", description = "A server error")
    @PostMapping(value = "/student/{id}/grade/{clazzId}/{grade}", produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.ACCEPTED)
    public @NonNull ResponseEntity<StudentResponse> setGrade(@NonNull @PathVariable String id,
                                                             @NonNull @PathVariable String clazzId,
                                                             @NonNull @PathVariable @Min(1) @Max(6) BigDecimal grade) {
        studentService.addGrade(id, clazzId, grade);
        return ResponseEntity.accepted().build();
    }

    @Operation(summary = "Gets the grades of a student")
    @ApiResponse(responseCode = "200", description = "The grades")
    @ApiResponse(responseCode = "404", description = "The student not found")
    @ApiResponse(responseCode = "500", description = "A server error")
    @GetMapping(value = "/student/{id}/grades", produces = APPLICATION_JSON_VALUE)
    public @NonNull ResponseEntity<List<GradeResponse>> getGrades(@NonNull @PathVariable String id) {
        return ResponseEntity.ok(gradeMapper.asResponse(studentService.getGrades(id)));
    }


    @Operation(summary = "Finds all students")
    @ApiResponse(responseCode = "200", description = "The students")
    @GetMapping(value = "/students", produces = APPLICATION_JSON_VALUE)
    public @NonNull ResponseEntity<List<StudentResponse>> findAll() {
        return ResponseEntity.ok(studentMapper.asResponse(studentService.findAll()));
    }
}