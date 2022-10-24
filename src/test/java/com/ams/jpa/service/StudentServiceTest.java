package com.ams.jpa.service;

import com.ams.jpa.component.mapper.ClazzMapper;
import com.ams.jpa.component.mapper.ClazzMapperImpl;
import com.ams.jpa.component.mapper.GradeMapper;
import com.ams.jpa.component.mapper.GradeMapperImpl;
import com.ams.jpa.component.mapper.HeadTeacherMapper;
import com.ams.jpa.component.mapper.HeadTeacherMapperImpl;
import com.ams.jpa.component.mapper.StudentMapper;
import com.ams.jpa.component.mapper.StudentMapperImpl;
import com.ams.jpa.model.dto.StudentDto;
import com.ams.jpa.model.entity.Clazz;
import com.ams.jpa.model.entity.HeadTeacher;
import com.ams.jpa.model.rest.request.CreateStudentRequest;
import com.ams.jpa.repository.ClazzRepository;
import com.ams.jpa.repository.HeadTeacherRepository;
import com.ams.jpa.repository.StudentRepository;
import com.ams.jpa.service.impl.StudentService;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

import java.math.BigDecimal;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
@Slf4j
public class StudentServiceTest {
    @TestConfiguration
    static class TestConfig {
        @Bean
        public ClazzMapper clazzMapper() {
            return new ClazzMapperImpl();
        }
        @Bean
        public HeadTeacherMapper headTeacherMapper() {
            return new HeadTeacherMapperImpl();
        }
        @Bean
        public GradeMapper gradeMapper() {
            return new GradeMapperImpl();
        }
        @Bean
        public StudentMapper studentMapper() {
            return new StudentMapperImpl();
        }
    }

    @Autowired
    protected StudentRepository studentRepository;
    @Autowired
    protected HeadTeacherRepository headTeacherRepository;
    @Autowired
    protected ClazzRepository clazzRepository;
    @Autowired
    protected StudentMapper studentMapper;
    @Autowired
    protected GradeMapper gradeMapper;

    protected StudentService studentService;
    protected String headTeacherId;
    protected String clazzId;

    @BeforeEach
    public void setup() {
        studentService = new StudentService(studentRepository, headTeacherRepository, clazzRepository, studentMapper, gradeMapper);
        studentRepository.deleteAll();

        if(headTeacherId == null) {
            headTeacherId = headTeacherRepository.save(new HeadTeacher(UUID.randomUUID().toString())).getId();
        }
        if(clazzId == null) {
            clazzId = clazzRepository.save(new Clazz(UUID.randomUUID().toString())).getId();
        }
    }

    @Test
    public void create() {
        assertTrue(studentService.getAll().isEmpty());
        createInternal(UUID.randomUUID().toString(), UUID.randomUUID().toString(), headTeacherId);
        assertFalse(studentService.getAll().isEmpty());
    }

    @Test
    public void addGrade() {
        assertTrue(studentService.getAll().isEmpty());
        StudentDto student = createInternal(UUID.randomUUID().toString(), UUID.randomUUID().toString(), headTeacherId);

        BigDecimal grade = BigDecimal.valueOf(ThreadLocalRandom.current().nextLong(1,6));
        studentService.addGrade(student.getId(), clazzId, grade);

        student = studentService.getById(student.getId()).orElseThrow(RuntimeException::new);
        assertFalse(student.getGrades().isEmpty());
        assertEquals(clazzId, student.getGrades().get(0).getClazz().getId());
        assertEquals(grade, student.getGrades().get(0).getGradeValue());
    }

    @Test
    public void getAll() {
        int count = ThreadLocalRandom.current().nextInt(20);
        for(int i=0;i<count;i++) {
            createInternal(UUID.randomUUID().toString(), UUID.randomUUID().toString(), headTeacherId);
        }
        assertEquals(count, studentService.getAll().size());
    }

    @Test
    public void getById() {
        assertTrue(studentService.getAll().isEmpty());
        StudentDto dto = createInternal(UUID.randomUUID().toString(), UUID.randomUUID().toString(), headTeacherId);

        assertFalse(studentService.getById(dto.getId()).isEmpty());
    }

    @Test
    public void validateMapper() {
        String firstname = UUID.randomUUID().toString();
        String lastname = UUID.randomUUID().toString();
        StudentDto dto = createInternal(firstname, lastname, headTeacherId);

        assertEquals(lastname, dto.getLastname());
        assertEquals(firstname, dto.getFirstname());
        assertEquals(headTeacherId, dto.getHeadTeacherDto().getId());

        assertNotNull(dto.getCreateDate());
        assertNotNull(dto.getModifyDate());
        assertNotNull(dto.getId());
    }

    private StudentDto createInternal(@NonNull String firstname, @NonNull String lastname, @NonNull String headTeacherId) {
        return studentService.create(CreateStudentRequest.builder()
                        .firstName(firstname)
                        .lastName(lastname)
                        .headTeacherId(headTeacherId)
                .build());
    }
}
