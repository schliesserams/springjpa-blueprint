package com.ams.jpa.mapper;

import com.ams.jpa.model.entity.HeadTeacher;
import com.ams.jpa.model.rest.response.HeadTeacherDto;
import lombok.NonNull;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface HeadTeacherMapper {
    @NonNull HeadTeacherDto asDto(@NonNull HeadTeacher headTeacher);
}
