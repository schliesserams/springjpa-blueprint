package com.ams.jpa.controller;

import com.ams.jpa.component.mapper.HeadTeacherMapper;
import com.ams.jpa.model.rest.request.CreateHeadTeacherRequest;
import com.ams.jpa.model.rest.response.HeadTeacherResponse;
import com.ams.jpa.service.IHeadTeacherService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;
import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;


@RestController
@RequestMapping("/v1")
@RequiredArgsConstructor
@Tag(name = "Head Teacher", description = "Handles the head teachers in school")
@Slf4j
@Validated
public class HeadTeacherController {
    private final IHeadTeacherService headTeacherService;
    private final HeadTeacherMapper mapper;

    @Operation(summary = "Creates a head teacher")
    @ApiResponse(responseCode = "200", description = "The created head teacher")
    @ApiResponse(responseCode = "400", description = "The request is not valid")
    @ApiResponse(responseCode = "409", description = "A head teacher with that name already exists")
    @ApiResponse(responseCode = "500", description = "A server error")
    @PostMapping(value = "/headteacher", produces = APPLICATION_JSON_VALUE)
    public @NonNull ResponseEntity<HeadTeacherResponse> create(@NonNull @RequestBody @Valid CreateHeadTeacherRequest headTeacher) {
        return ResponseEntity.ok(mapper.asResponse(headTeacherService.create(headTeacher)));
    }

    @Operation(summary = "Gets a head teacher")
    @ApiResponse(responseCode = "200", description = "The head teacher")
    @ApiResponse(responseCode = "404", description = "The head teacher was not found")
    @ApiResponse(responseCode = "500", description = "A server error")
    @GetMapping(value = "/headteacher/{id}", produces = APPLICATION_JSON_VALUE)
    public @NonNull ResponseEntity<HeadTeacherResponse> findById(@NonNull @PathVariable String id) {
        return ResponseEntity.ok(mapper.asResponse(headTeacherService.findById(id)
                .orElseThrow(EntityNotFoundException::new)));
    }

    @Operation(summary = "Gets a head teacher by name")
    @ApiResponse(responseCode = "200", description = "The head teacher")
    @ApiResponse(responseCode = "404", description = "The head teacher was not found")
    @ApiResponse(responseCode = "500", description = "A server error")
    @GetMapping(value = "/headteacher/name/{name}", produces = APPLICATION_JSON_VALUE)
    public @NonNull ResponseEntity<HeadTeacherResponse> findByName(@NonNull @PathVariable String name) {
        return ResponseEntity.ok(mapper.asResponse(headTeacherService.findByName(name)
                .orElseThrow(EntityNotFoundException::new)));
    }

    @Operation(summary = "Finds all head teachers")
    @ApiResponse(responseCode = "200", description = "The head teachers")
    @GetMapping(value = "/headteachers", produces = APPLICATION_JSON_VALUE)
    public @NonNull ResponseEntity<List<HeadTeacherResponse>> findAll() {
        return ResponseEntity.ok(mapper.asResponse(headTeacherService.findAll()));
    }
}