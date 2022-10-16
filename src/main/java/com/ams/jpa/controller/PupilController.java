package com.ams.jpa.controller;

import com.ams.jpa.model.entity.Pupil;
import com.ams.jpa.model.rest.request.CreatePupilDto;
import com.ams.jpa.service.IPupilService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;


@RestController
@RequestMapping("/v1")
@RequiredArgsConstructor
@Tag(name = "Pupils", description = "Handles the pupils in school")
@Slf4j
public class PupilController {
    private final IPupilService pupilService;

    @Operation(summary = "Creates a pupil")
    @ApiResponse(responseCode = "200", description = "The created pupil")
    @ApiResponse(responseCode = "500", description = "A server error")
    @PostMapping(value = "/pupil", produces = APPLICATION_JSON_VALUE)
    public @NonNull ResponseEntity<Pupil> create(@NonNull @RequestBody CreatePupilDto createPupilDto) {
        return ResponseEntity.ok(pupilService.create(createPupilDto));
    }

    @Operation(summary = "Gets a pupil")
    @ApiResponse(responseCode = "200", description = "The created pupil")
    @ApiResponse(responseCode = "404", description = "The pupil was not found")
    @ApiResponse(responseCode = "500", description = "A server error")
    @GetMapping(value = "/pupil/{id}", produces = APPLICATION_JSON_VALUE)
    public @NonNull ResponseEntity<Optional<Pupil>> findById(@NonNull @PathVariable String id) {
        return ResponseEntity.ok(pupilService.findById(id));
    }

    @Operation(summary = "Finds all pupil")
    @ApiResponse(responseCode = "200", description = "The pupils")
    @GetMapping(value = "/pupils", produces = APPLICATION_JSON_VALUE)
    public @NonNull ResponseEntity<List<Pupil>> findAll() {
        return ResponseEntity.ok(pupilService.findAll());
    }
}