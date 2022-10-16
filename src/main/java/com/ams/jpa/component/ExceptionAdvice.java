package com.ams.jpa.component;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.persistence.EntityNotFoundException;
import javax.validation.ConstraintViolationException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.time.ZonedDateTime;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

@RestControllerAdvice
@Slf4j
public class ExceptionAdvice {
    @ExceptionHandler(EntityNotFoundException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public @NonNull ErrorMessage entityNotFoundException(@NonNull EntityNotFoundException e) {
        return ErrorMessage.builder()
                .message(e.getMessage())
                .description(ExceptionUtils.getStackTrace(e))
                .build();
    }

    @ExceptionHandler(SQLIntegrityConstraintViolationException.class)
    @ResponseStatus(value = HttpStatus.CONFLICT)
    public @NonNull ErrorMessage integrityConstraintViolationException(@NonNull SQLIntegrityConstraintViolationException e) {
        return ErrorMessage.builder()
                .message(e.getMessage())
                .description(ExceptionUtils.getStackTrace(e))
                .build();
    }

    @ExceptionHandler({MethodArgumentNotValidException.class, ConstraintViolationException.class})
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public @NonNull ErrorMessage constraintViolationException(@NonNull Exception e) {
        return ErrorMessage.builder()
                .message(e.getMessage())
                .build();
    }

    @Data
    @Builder
    @JsonInclude(value = NON_NULL)
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static final class ErrorMessage {
        @JsonProperty(value = "timestamp", required = true)
        @Schema(description = "the datetime when the error happened")
        @Builder.Default
        private ZonedDateTime timestamp = ZonedDateTime.now();

        @JsonProperty(value = "message", required = true)
        @Schema(description = "a short message describing the error")
        private String message;

        @JsonProperty(value = "description")
        @Schema(description = "an optional description of the error")
        private String description;
    }
}