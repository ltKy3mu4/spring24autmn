package ru.mpei.measurementservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.mpei.measurementservice.model.DataItem;
import ru.mpei.measurementservice.repository.DummyStringRepo;

import java.util.List;

@RestController
public class MeasurementsController {

    @Autowired
    private DummyStringRepo repo;

    @GetMapping("/data")
    public List<DataItem> getAllData(){
        return repo.getAll();
    }

    @GetMapping("data/value")
    public ResponseEntity<Double> getValueByTag(@RequestParam String tag){
        Double valueByTag = repo.getValueByTag(tag);
        if (valueByTag == null){
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(valueByTag);
        }
    }


    @PostMapping("/data")
    public void save(@RequestBody DataItem item){
        repo.add(item);
    }

    @DeleteMapping("/data")
    public ResponseEntity<?> delete(@RequestBody DataItem item){
        boolean res = repo.remove(item);
        if (res){
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.badRequest().build();
        }
    }


    @DeleteMapping("/all")
    public void delete(){
        repo.removeAll();
    }


}
