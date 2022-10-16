package com.ams.jpa.service.impl;

import com.ams.jpa.component.mapper.GradeMapper;
import com.ams.jpa.component.mapper.StudentMapper;
import com.ams.jpa.model.dto.GradeDto;
import com.ams.jpa.model.dto.StudentDto;
import com.ams.jpa.model.entity.Clazz;
import com.ams.jpa.model.entity.Grade;
import com.ams.jpa.model.entity.HeadTeacher;
import com.ams.jpa.model.entity.Student;
import com.ams.jpa.model.rest.request.CreateStudentRequest;
import com.ams.jpa.repository.ClazzRepository;
import com.ams.jpa.repository.HeadTeacherRepository;
import com.ams.jpa.repository.StudentRepository;
import com.ams.jpa.service.IStudentService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class StudentService implements IStudentService {
    private final StudentRepository studentRepository;
    private final HeadTeacherRepository headTeacherRepository;
    private final ClazzRepository clazzRepository;
    private final StudentMapper studentMapper;
    private final GradeMapper gradeMapper;

    @Transactional
    @Override
    public @NonNull StudentDto create(@NonNull CreateStudentRequest createStudent) {
        final HeadTeacher headTeacher = headTeacherRepository.findById(createStudent.getHeadTeacherId())
                .orElseThrow(EntityNotFoundException::new);
        return studentMapper.asDto(studentRepository.save(Student.builder()
                .firstname(createStudent.getFirstName())
                .lastname(createStudent.getLastName())
                .headTeacher(headTeacher)
                .build()));
    }

    @Transactional(readOnly = true)
    @Override
    public @NonNull Optional<StudentDto> findById(@NonNull String id) {
        return studentRepository.findById(id)
                .map(studentMapper::asDto);
    }

    @Transactional(readOnly = true)
    @Override
    public @NonNull List<StudentDto> findAll() {
        return studentRepository.findAll()
                .stream().map(studentMapper::asDto).toList();
    }

    @Transactional(readOnly = true)
    @Override
    public @NonNull List<GradeDto> getGrades(@NonNull String id) {
        Student student = studentRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        return gradeMapper.asDto(student.getGrades());
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public @NonNull StudentDto addGrade(@NonNull String id, @NonNull String clazzId, @NonNull BigDecimal gradeValue) {
        Student student = studentRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        Clazz clazz = clazzRepository.findById(clazzId).orElseThrow(EntityNotFoundException::new);

        Grade grade = student.getGrades().stream()
                .filter(g -> clazz.equals(g.getClazz())).findFirst().orElse(new Grade());
        grade.setGradeValue(gradeValue);
        grade.setStudent(student);
        grade.setClazz(clazz);
        student.getGrades().add(grade);
        return studentMapper.asDto(studentRepository.save(student));
    }
}
