package ru.mpei.measurementservice.service.validation;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.mpei.measurementservice.model.DataItemDto;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ValidationServiceTest {
    @Autowired
    private ValidationService service;

    @Test
    public void validationFailed1(){
        DataItemDto dto = new DataItemDto("tag", 10);
        boolean validate = service.validate(dto);
        Assertions.assertFalse(validate);
    }

    @Test
    public void validationFailed2(){
        DataItemDto dto = new DataItemDto("tag", -1.0);
        boolean validate = service.validate(dto);
        Assertions.assertFalse(validate);
    }


    @Test
    public void validationOk(){
        DataItemDto dto = new DataItemDto("tag", 2.0);
        boolean validate = service.validate(dto);
        Assertions.assertTrue(validate);
    }
}