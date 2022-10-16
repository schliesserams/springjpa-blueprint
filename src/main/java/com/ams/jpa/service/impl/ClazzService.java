package com.ams.jpa.service.impl;

import com.ams.jpa.component.mapper.ClazzMapper;
import com.ams.jpa.model.dto.ClazzDto;
import com.ams.jpa.model.entity.Clazz;
import com.ams.jpa.model.rest.request.CreateClazzRequest;
import com.ams.jpa.repository.ClazzRepository;
import com.ams.jpa.service.IClazzService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ClazzService implements IClazzService {
    private final ClazzRepository clazzRepository;
    private final ClazzMapper mapper;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public @NonNull ClazzDto create(@NonNull CreateClazzRequest clazz) {
        return mapper.asDto(clazzRepository.save(Clazz.builder().name(clazz.getName()).build()));
    }

    @Transactional(readOnly = true)
    @Override
    public @NonNull List<ClazzDto> getAll() {
        return clazzRepository.findAll().stream()
                .map(mapper::asDto).toList();
    }

    @Transactional(readOnly = true)
    @Override
    public @NonNull Optional<ClazzDto> getById(@NonNull String id) {
        return clazzRepository.findById(id)
                .map(mapper::asDto);
    }

    @Transactional(readOnly = true)
    @Override
    public @NonNull Optional<ClazzDto> getByName(@NonNull String name) {
        return clazzRepository.findByName(name)
                .map(mapper::asDto);
    }
}
