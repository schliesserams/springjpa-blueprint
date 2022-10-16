package com.ams.jpa.component.mapper;

import com.ams.jpa.model.dto.HeadTeacherDto;
import com.ams.jpa.model.entity.HeadTeacher;
import com.ams.jpa.model.rest.response.HeadTeacherResponse;
import lombok.NonNull;
import org.mapstruct.Mapper;

import java.util.Collection;
import java.util.List;

@Mapper(componentModel = "spring")
public interface HeadTeacherMapper {
    @NonNull HeadTeacherDto asDto(@NonNull HeadTeacher headTeacher);
    @NonNull List<HeadTeacherDto> asDto(@NonNull Collection<HeadTeacher> headTeacher);

    @NonNull HeadTeacherResponse asResponse(@NonNull HeadTeacherDto headTeacher);

    @NonNull List<HeadTeacherResponse> asResponse(@NonNull Collection<HeadTeacherDto> headTeachers);
}
