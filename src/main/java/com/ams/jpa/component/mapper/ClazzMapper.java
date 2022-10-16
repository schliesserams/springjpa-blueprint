package com.ams.jpa.component.mapper;

import com.ams.jpa.model.dto.ClazzDto;
import com.ams.jpa.model.entity.Clazz;
import com.ams.jpa.model.rest.response.ClazzResponse;
import lombok.NonNull;
import org.mapstruct.Mapper;

import java.util.Collection;
import java.util.List;

@Mapper(componentModel = "spring")
public interface ClazzMapper {
    @NonNull ClazzDto asDto(@NonNull Clazz clazz);
    @NonNull List<ClazzDto> asDto(@NonNull Collection<Clazz> clazzes);

    @NonNull ClazzResponse asResponse(@NonNull ClazzDto clazz);

    @NonNull List<ClazzResponse> asResponse(@NonNull Collection<ClazzDto> clazzes);
}
