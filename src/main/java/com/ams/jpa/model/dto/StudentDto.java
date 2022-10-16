package com.ams.jpa.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StudentDto implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private String id;
    private String lastname;
    private String firstname;
    private HeadTeacherDto headTeacherDto;
    @Builder.Default
    private List<GradeDto> grades = new ArrayList<>();
    private ZonedDateTime modifyDate;
    private ZonedDateTime createDate;
}
