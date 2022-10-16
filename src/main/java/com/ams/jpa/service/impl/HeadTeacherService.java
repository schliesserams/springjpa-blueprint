package com.ams.jpa.service.impl;

import com.ams.jpa.model.entity.HeadTeacher;
import com.ams.jpa.model.rest.request.CreateHeadTeacherDto;
import com.ams.jpa.repository.HeadTeacherRepository;
import com.ams.jpa.service.IHeadTeacherService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class HeadTeacherService implements IHeadTeacherService {
    private final HeadTeacherRepository headTeacherRepository;

    @Transactional(readOnly = true)
    @Override
    public @NonNull List<HeadTeacher> findAll() {
        return headTeacherRepository.findAll();
    }

    @Transactional(readOnly = true)
    @Override
    public @NonNull Optional<HeadTeacher> findById(@NonNull String id) {
        return headTeacherRepository.findById(id);
    }

    @Transactional(readOnly = true)
    @Override
    public @NonNull Optional<HeadTeacher> findByName(@NonNull String name) {
        return headTeacherRepository.findByName(name);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public @NonNull HeadTeacher create(@NonNull CreateHeadTeacherDto createHeadTeacher) {
        return headTeacherRepository.save(HeadTeacher.builder().name(createHeadTeacher.getName()).build());
    }
}
