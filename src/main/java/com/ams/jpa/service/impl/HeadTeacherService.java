package com.ams.jpa.service.impl;

import com.ams.jpa.component.mapper.HeadTeacherMapper;
import com.ams.jpa.model.dto.HeadTeacherDto;
import com.ams.jpa.model.entity.HeadTeacher;
import com.ams.jpa.model.rest.request.CreateHeadTeacherRequest;
import com.ams.jpa.repository.HeadTeacherRepository;
import com.ams.jpa.service.IHeadTeacherService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class HeadTeacherService implements IHeadTeacherService {
    private final HeadTeacherRepository headTeacherRepository;
    private final HeadTeacherMapper mapper;

    @Transactional(readOnly = true)
    @Override
    public @NonNull List<HeadTeacherDto> getAll() {
        LOG.info("Getting all HeadTeachers...");
        return headTeacherRepository.findAll().stream()
                .map(mapper::asDto).toList();
    }

    @Transactional(readOnly = true)
    @Override
    public @NonNull Optional<HeadTeacherDto> getById(@NonNull String id) {
        LOG.info("Getting HeadTeacher by Id {}...", id);
        return headTeacherRepository.findById(id)
                .map(mapper::asDto);
    }

    @Transactional(readOnly = true)
    @Override
    public @NonNull Optional<HeadTeacherDto> getByName(@NonNull String name) {
        LOG.info("Getting HeadTeacher by Name {}...", name);
        return headTeacherRepository.findByName(name)
                .map(mapper::asDto);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public @NonNull HeadTeacherDto create(@NonNull CreateHeadTeacherRequest headTeacher) {
        LOG.info("Creating HeadTeacher {}...", headTeacher);
        return mapper.asDto(headTeacherRepository.save(HeadTeacher.builder()
                .name(headTeacher.getName())
                .build()));
    }
}
