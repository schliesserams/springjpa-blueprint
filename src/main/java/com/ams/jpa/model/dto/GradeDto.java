package com.ams.jpa.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.ZonedDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GradeDto implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private String id;
    private ClazzDto clazz;
    private BigDecimal gradeValue;
    private ZonedDateTime modifyDate;
    private ZonedDateTime createDate;
}
