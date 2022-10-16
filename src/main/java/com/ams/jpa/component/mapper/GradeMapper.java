package com.ams.jpa.component.mapper;

import com.ams.jpa.model.dto.GradeDto;
import com.ams.jpa.model.entity.Grade;
import com.ams.jpa.model.rest.response.GradeResponse;
import lombok.NonNull;
import org.mapstruct.Mapper;

import java.util.Collection;
import java.util.List;

@Mapper(componentModel = "spring")
public interface GradeMapper {
    @NonNull GradeDto asDto(@NonNull Grade grade);

    @NonNull List<GradeDto> asDto(@NonNull Collection<Grade> grades);

    @NonNull GradeResponse asResponse(@NonNull GradeDto grade);

    @NonNull List<GradeResponse> asResponse(@NonNull Collection<GradeDto> grades);
}
