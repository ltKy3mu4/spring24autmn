package ru.mpei.measurementservice.service.validation;

import ru.mpei.measurementservice.model.DataItemDto;

public interface MValidator {

    boolean check(DataItemDto dto);

}
