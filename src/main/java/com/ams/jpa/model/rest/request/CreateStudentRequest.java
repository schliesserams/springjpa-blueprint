package com.ams.jpa.model.rest.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotBlank;
import java.io.Serial;
import java.io.Serializable;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(value = NON_NULL)
@Validated
public class CreateStudentRequest implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @NotBlank
    @JsonProperty(value = "firstName", required = true)
    private String firstName;

    @NotBlank
    @JsonProperty(value = "lastName", required = true)
    private String lastName;

    @NotBlank
    @JsonProperty(value = "headTeacherId", required = true)
    private String headTeacherId;
}
