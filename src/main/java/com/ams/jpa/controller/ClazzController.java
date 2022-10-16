package com.ams.jpa.controller;

import com.ams.jpa.component.mapper.ClazzMapper;
import com.ams.jpa.model.rest.request.CreateClazzRequest;
import com.ams.jpa.model.rest.response.ClazzResponse;
import com.ams.jpa.service.IClazzService;
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
@Tag(name = "Clazz", description = "Handles the classes in school")
@Slf4j
@Validated
public class ClazzController {
    private final IClazzService clazzService;
    private final ClazzMapper mapper;

    @Operation(summary = "Creates a clazz")
    @ApiResponse(responseCode = "200", description = "The created clazz")
    @ApiResponse(responseCode = "400", description = "The request is not valid")
    @ApiResponse(responseCode = "409", description = "A clazz with that name already exists")
    @ApiResponse(responseCode = "500", description = "A server error")
    @PostMapping(value = "/clazz", produces = APPLICATION_JSON_VALUE)
    public @NonNull ResponseEntity<ClazzResponse> create(@NonNull @RequestBody @Valid CreateClazzRequest clazz) {
        return ResponseEntity.ok(mapper.asResponse(clazzService.create(clazz)));
    }

    @Operation(summary = "Gets a clazz")
    @ApiResponse(responseCode = "200", description = "The clazz")
    @ApiResponse(responseCode = "404", description = "The clazz was not found")
    @ApiResponse(responseCode = "500", description = "A server error")
    @GetMapping(value = "/clazz/{id}", produces = APPLICATION_JSON_VALUE)
    public @NonNull ResponseEntity<ClazzResponse> getById(@NonNull @PathVariable String id) {
        return ResponseEntity.ok(mapper.asResponse(clazzService.getById(id)
                .orElseThrow(EntityNotFoundException::new)));
    }

    @Operation(summary = "Gets a clazz by name")
    @ApiResponse(responseCode = "200", description = "The clazz")
    @ApiResponse(responseCode = "404", description = "The clazz was not found")
    @ApiResponse(responseCode = "500", description = "A server error")
    @GetMapping(value = "/clazz/name/{name}", produces = APPLICATION_JSON_VALUE)
    public @NonNull ResponseEntity<ClazzResponse> getByName(@NonNull @PathVariable String name) {
        return ResponseEntity.ok(mapper.asResponse(clazzService.getByName(name)
                .orElseThrow(EntityNotFoundException::new)));
    }

    @Operation(summary = "Finds all clazzs")
    @ApiResponse(responseCode = "200", description = "The clazzs")
    @GetMapping(value = "/clazzs", produces = APPLICATION_JSON_VALUE)
    public @NonNull ResponseEntity<List<ClazzResponse>> getAll() {
        return ResponseEntity.ok(mapper.asResponse(clazzService.getAll()));
    }
}