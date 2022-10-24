package com.ams.jpa.service;

import com.ams.jpa.component.mapper.HeadTeacherMapper;
import com.ams.jpa.component.mapper.HeadTeacherMapperImpl;
import com.ams.jpa.model.dto.HeadTeacherDto;
import com.ams.jpa.model.rest.request.CreateHeadTeacherRequest;
import com.ams.jpa.repository.HeadTeacherRepository;
import com.ams.jpa.service.impl.HeadTeacherService;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
@Slf4j
public class HeadTeacherServiceTest {
    @TestConfiguration
    static class TestConfig {
        @Bean
        public HeadTeacherMapper headTeacherMapper() {
            return new HeadTeacherMapperImpl();
        }
    }
    @Autowired
    protected HeadTeacherRepository headTeacherRepository;
    @Autowired
    protected HeadTeacherMapper headTeacherMapper;

    protected HeadTeacherService headTeacherService;

    @BeforeEach
    public void setup() {
        headTeacherService = new HeadTeacherService(headTeacherRepository, headTeacherMapper);
        headTeacherRepository.deleteAll();
    }

    @Test
    public void create() {
        assertTrue(headTeacherService.getAll().isEmpty());
        createInternal(UUID.randomUUID().toString());
        assertFalse(headTeacherService.getAll().isEmpty());
    }

    @Test
    public void getAll() {
        int count = ThreadLocalRandom.current().nextInt(20);
        for(int i=0;i<count;i++) {
            createInternal(UUID.randomUUID().toString());
        }
        assertEquals(count, headTeacherService.getAll().size());
    }

    @Test
    public void getById() {
        assertTrue(headTeacherService.getAll().isEmpty());
        HeadTeacherDto dto = createInternal(UUID.randomUUID().toString());

        assertFalse(headTeacherService.getById(dto.getId()).isEmpty());
    }

    @Test
    public void getByName() {
        assertTrue(headTeacherService.getAll().isEmpty());
        HeadTeacherDto dto = createInternal(UUID.randomUUID().toString());

        assertFalse(headTeacherService.getByName(dto.getName()).isEmpty());
    }

    @Test
    public void validateMapper() {
        String name = UUID.randomUUID().toString();
        HeadTeacherDto dto = createInternal(name);
        assertEquals(name, dto.getName());
        assertNotNull(dto.getCreateDate());
        assertNotNull(dto.getModifyDate());
        assertNotNull(dto.getId());
    }

    private HeadTeacherDto createInternal(@NonNull String name) {
        return headTeacherService.create(CreateHeadTeacherRequest.builder()
                .name(name)
                .build());
    }
}
