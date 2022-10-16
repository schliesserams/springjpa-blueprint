package com.ams.jpa.model.rest.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.ZonedDateTime;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(value = NON_NULL)
@Schema(description = "A student")
public class StudentResponse implements Serializable {
    @JsonProperty(value = "id", required = true)
    private String id;

    @JsonProperty(value = "lastname", required = true)
    private String lastname;

    @JsonProperty(value = "firstname", required = true)
    private String firstname;

    @JsonProperty(value = "headTeacher", required = true)
    private HeadTeacher headTeacher;

    @JsonProperty(value = "modifyDate", required = true)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSZ", timezone = "UTC")
    private ZonedDateTime modifyDate;

    @JsonProperty(value = "createDate", required = true)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSZ", timezone = "UTC")
    private ZonedDateTime createDate;

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    @JsonIgnoreProperties(ignoreUnknown = true)
    @JsonInclude(value = NON_NULL)
    public static final class HeadTeacher implements Serializable {
        @JsonProperty(value = "id", required = true)
        private String id;

        @JsonProperty(value = "name", required = true)
        private String name;
    }
}
