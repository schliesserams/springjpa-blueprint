package com.ams.jpa.component.mapper;

import com.ams.jpa.model.dto.StudentDto;
import com.ams.jpa.model.entity.Student;
import com.ams.jpa.model.rest.response.StudentResponse;
import lombok.NonNull;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.Collection;
import java.util.List;

@Mapper(componentModel = "spring", uses = HeadTeacherMapper.class)
public interface StudentMapper {
    @Mapping(source = "headTeacher", target = "headTeacherDto")
    @NonNull StudentDto asDto(@NonNull Student student);

    @NonNull List<StudentDto> asDto(@NonNull Collection<Student> students);

    @Mapping(source = "headTeacherDto", target = "headTeacher")
    @NonNull StudentResponse asResponse(@NonNull StudentDto student);

    @NonNull List<StudentResponse> asResponse(@NonNull Collection<StudentDto> students);

}
