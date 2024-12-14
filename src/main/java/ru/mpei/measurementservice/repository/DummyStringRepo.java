package ru.mpei.measurementservice.repository;

import org.springframework.stereotype.Component;
import ru.mpei.measurementservice.model.DataItemDto;

import java.util.ArrayList;
import java.util.List;

@Component
public class DummyStringRepo {

    private List<DataItemDto> storage = new ArrayList<>(
            List.of(new DataItemDto("hello", 2.0), new DataItemDto("str2", 4.0)));

    public void add(DataItemDto di){
        storage.add(di);
    }

    public List<DataItemDto> getAll(){
        return storage;
    }

    public boolean remove(DataItemDto di){
        return storage.remove(di);
    }

    public void removeAll(){
        if (storage.size() > 0){
            storage.clear();
        } else {
            throw new RuntimeException("Storage already empty!!!");
        }
    }

    public Double getValueByTag(String tag){
        for (DataItemDto dataItemDto : storage) {
            if (dataItemDto.getTag().equals(tag)){
                return dataItemDto.getValue();
            }
        }
        return null;
    }

}
