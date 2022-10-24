package com.ams.jpa.service;

import com.ams.jpa.component.mapper.ClazzMapper;
import com.ams.jpa.component.mapper.ClazzMapperImpl;
import com.ams.jpa.model.dto.ClazzDto;
import com.ams.jpa.model.rest.request.CreateClazzRequest;
import com.ams.jpa.repository.ClazzRepository;
import com.ams.jpa.service.impl.ClazzService;
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
public class ClazzServiceTest {
    @TestConfiguration
    static class TestConfig {
        @Bean
        public ClazzMapper clazzMapper() {
            return new ClazzMapperImpl();
        }
    }

    @Autowired
    protected ClazzRepository clazzRepository;
    @Autowired
    protected ClazzMapper clazzMapper;

    protected ClazzService clazzService;

    @BeforeEach
    public void setup() {
        clazzService = new ClazzService(clazzRepository, clazzMapper);
        clazzRepository.deleteAll();
    }

    @Test
    public void create() {
        assertTrue(clazzService.getAll().isEmpty());
        createInternal(UUID.randomUUID().toString());
        assertFalse(clazzService.getAll().isEmpty());
    }

    @Test
    public void getAll() {
        int count = ThreadLocalRandom.current().nextInt(20);
        for(int i=0;i<count;i++) {
            createInternal(UUID.randomUUID().toString());
        }
        assertEquals(count, clazzService.getAll().size());
    }

    @Test
    public void getById() {
        assertTrue(clazzService.getAll().isEmpty());
        ClazzDto dto = createInternal(UUID.randomUUID().toString());

        assertFalse(clazzService.getById(dto.getId()).isEmpty());
    }

    @Test
    public void getByName() {
        assertTrue(clazzService.getAll().isEmpty());
        ClazzDto dto = createInternal(UUID.randomUUID().toString());

        assertFalse(clazzService.getByName(dto.getName()).isEmpty());
    }

    @Test
    public void validateMapper() {
        String name = UUID.randomUUID().toString();
        ClazzDto dto = createInternal(name);
        assertEquals(name, dto.getName());
        assertNotNull(dto.getCreateDate());
        assertNotNull(dto.getModifyDate());
        assertNotNull(dto.getId());
    }

    private ClazzDto createInternal(@NonNull String name) {
        return clazzService.create(CreateClazzRequest.builder()
                .name(name)
                .build());
    }
}
