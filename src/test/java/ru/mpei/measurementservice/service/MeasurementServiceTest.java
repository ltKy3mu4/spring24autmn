package ru.mpei.measurementservice.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.mpei.measurementservice.model.DataItemDto;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class MeasurementServiceTest {

    @Autowired
    MeasurementService service;

    @Test
    void testSave(){
        DataItemDto t = service.getByTag("t");
        Assertions.assertTrue(t.getTag() == null);

        service.save(new DataItemDto("t", 2.0));

        t = service.getByTag("t");
        Assertions.assertTrue(t.getValue() == 2.0);
    }
}