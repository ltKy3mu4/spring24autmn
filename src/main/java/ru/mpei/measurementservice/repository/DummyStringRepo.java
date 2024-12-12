package ru.mpei.measurementservice.repository;

import org.springframework.stereotype.Component;
import ru.mpei.measurementservice.model.DataItem;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class DummyStringRepo {

    private List<DataItem> storage = new ArrayList<>(
            List.of(new DataItem("hello", 2.0), new DataItem("str2", 4.0)));

    public void add(DataItem di){
        storage.add(di);
    }

    public List<DataItem> getAll(){
        return storage;
    }

    public boolean remove(DataItem di){
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
        for (DataItem dataItem : storage) {
            if (dataItem.getTag().equals(tag)){
                return dataItem.getValue();
            }
        }
        return null;
    }

}
