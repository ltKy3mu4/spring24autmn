package ru.mpei.measurementservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.mpei.measurementservice.model.DataItemDto;
import ru.mpei.measurementservice.model.DataItemEntity;
import ru.mpei.measurementservice.repository.JpaRepository;
import ru.mpei.measurementservice.service.validation.ValidationService;
import java.util.List;
import java.util.Optional;

@Service
public class MeasurementService {

    @Autowired
    private ValidationService validationService;

    @Autowired
    private JpaRepository repository;

    public boolean save(DataItemDto dto){
        if (!validationService.validate(dto)){
            System.err.println("value validation failed "+dto.getValue());
            return false;
        }

        DataItemEntity dte = new DataItemEntity();
        dte.setTag(dto.getTag());
        dte.setValue(dto.getValue());
        repository.save(dte);
        return true;
    }

    public boolean delete(DataItemDto item){
        return repository.delete(item.getTag()) >0;
    }

    public void deleteAll(){
        repository.deleteAll();
    }

    public DataItemDto getByTag(String tag){
        Optional<DataItemEntity> opt = repository.get(tag);
        if (opt.isPresent()){
            return new DataItemDto(opt.get().getTag(), opt.get().getValue());
        } else {
            return new DataItemDto();
        }
    }

    public List<DataItemDto> getAll(){
        List<DataItemEntity> all = repository.getAll();
        return all.stream().map(e -> new DataItemDto(e.getTag(), e.getValue())).toList();
    }

}
