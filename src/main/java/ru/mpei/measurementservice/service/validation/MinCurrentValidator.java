package ru.mpei.measurementservice.service.validation;

import org.springframework.stereotype.Component;
import ru.mpei.measurementservice.model.DataItemDto;

@Component
public class MinCurrentValidator implements MValidator{
    @Override
    public boolean check(DataItemDto dto) {
        return dto.getValue() >= 0;
    }
}
