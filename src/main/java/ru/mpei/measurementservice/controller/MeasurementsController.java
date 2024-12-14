package ru.mpei.measurementservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.mpei.measurementservice.model.DataItemDto;
import ru.mpei.measurementservice.model.DataItemEntity;
import ru.mpei.measurementservice.repository.DummyStringRepo;
import ru.mpei.measurementservice.repository.JpaRepository;

import java.util.List;

@RestController
public class MeasurementsController {

    @Autowired
    private DummyStringRepo repo;

    @Autowired
    private JpaRepository jpaRepo;

    @GetMapping("/data")
    public List<DataItemDto> getAllData(){
        return repo.getAll();
    }

    @GetMapping("data/value")
    public ResponseEntity<DataItemDto> getValueByTag(@RequestParam String tag){
        DataItemEntity entity = jpaRepo.get(tag);
//        Double valueByTag = repo.getValueByTag(tag);
        if (entity == null){
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(new DataItemDto(entity.getTag(), entity.getValue()));
        }
    }


    @PostMapping("/data")
    public void save(@RequestBody DataItemDto item){
//        repo.add(item);
        DataItemEntity dte = new DataItemEntity();
        dte.setTag(item.getTag());
        dte.setValue(item.getValue());
        jpaRepo.save(dte);
    }

    @DeleteMapping("/data")
    public ResponseEntity<?> delete(@RequestBody DataItemDto item){
//        boolean res = repo.remove(item);
        int res = jpaRepo.delete(item.getTag());
        if (res > 0){
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.badRequest().build();
        }
    }


    @DeleteMapping("/all")
    public void delete(){
        repo.removeAll();
    }


    @GetMapping("/data/id")
    public ResponseEntity<DataItemDto> getById(@RequestParam long id){
        DataItemEntity entity = jpaRepo.get(id);
        if (entity == null){
            return ResponseEntity.notFound().build();
        }
        DataItemDto dto = new DataItemDto(entity.getTag(), entity.getValue());
        return ResponseEntity.ok(dto);
    }
}
