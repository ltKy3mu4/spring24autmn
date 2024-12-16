package ru.mpei.measurementservice.service.validation;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.mpei.measurementservice.model.DataItemDto;

import java.util.List;

@Service
public class ValidationService {
    @Autowired
    private List<MValidator> validators;

    public boolean validate(DataItemDto dto){
        for (MValidator validator : validators) {
            boolean res = validator.check(dto);
            if (!res) {
                return false;
            }
        }
        return true;
    }

}
