package com.ams.jpa.service.impl;

import com.ams.jpa.model.entity.HeadTeacher;
import com.ams.jpa.model.entity.Pupil;
import com.ams.jpa.model.enumeration.PersonStatus;
import com.ams.jpa.model.rest.request.CreateHeadTeacherDto;
import com.ams.jpa.model.rest.request.CreatePupilDto;
import com.ams.jpa.repository.PupilRepository;
import com.ams.jpa.service.IPupilService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PupilService implements IPupilService {
    private final PupilRepository pupilRepository;
    private final HeadTeacherService headTeacherService;

    @Override
    @Transactional
    public @NonNull Pupil create(@NonNull CreatePupilDto createPupil) {
        final HeadTeacher headTeacher = headTeacherService.findByName(createPupil.getHeadTeacherName())
                .orElse(headTeacherService.create(CreateHeadTeacherDto.builder()
                        .name(createPupil.getHeadTeacherName())
                        .build()));
        return pupilRepository.save(Pupil.builder()
                        .firstname(createPupil.getFirstName())
                        .lastname(createPupil.getLastName())
                        .status(PersonStatus.ACTIVE)
                        .headTeacher(headTeacher)
                .build());
    }

    @Override
    @Transactional(readOnly = true)
    public @NonNull Optional<Pupil> findById(@NonNull String id) {
        return pupilRepository.findById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public @NonNull Optional<Pupil> findByLastname(@NonNull String lastname) {
        return pupilRepository.findByLastname(lastname);
    }

    @Override
    @Transactional(readOnly = true)
    public @NonNull List<Pupil> findAll() {
        return pupilRepository.findAll();
    }
}
