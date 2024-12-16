package ru.mpei.measurementservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.mpei.measurementservice.model.DataItemDto;
import ru.mpei.measurementservice.model.DataItemEntity;
import ru.mpei.measurementservice.repository.DummyStringRepo;
import ru.mpei.measurementservice.repository.JpaRepository;
import ru.mpei.measurementservice.service.MeasurementService;

import java.util.List;

@RestController
public class MeasurementsController {

    @Autowired
    private MeasurementService service;


    @GetMapping("/data")
    public List<DataItemDto> getAllData(){
        return service.getAll();
    }

    @GetMapping("data/value")
    public ResponseEntity<DataItemDto> getValueByTag(@RequestParam String tag){
        DataItemDto dto = service.getByTag(tag);
        if (dto == null){
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(dto);
        }
    }


    @PostMapping("/data")
    public void save(@RequestBody DataItemDto item){
        service.save(item);
    }

    @DeleteMapping("/data")
    public ResponseEntity<?> delete(@RequestBody DataItemDto item){
        if (service.delete(item)){
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.badRequest().build();
        }
    }


    @DeleteMapping("/all")
    public void delete(){
        service.deleteAll();
    }


//    @GetMapping("/data/id")
//    public ResponseEntity<DataItemDto> getById(@RequestParam long id){
//        DataItemEntity entity = jpaRepo.get(id);
//        if (entity == null){
//            return ResponseEntity.notFound().build();
//        }
//        DataItemDto dto = new DataItemDto(entity.getTag(), entity.getValue());
//        return ResponseEntity.ok(dto);
//    }
}
