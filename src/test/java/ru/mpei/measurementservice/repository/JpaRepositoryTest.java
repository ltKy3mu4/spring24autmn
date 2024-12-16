package ru.mpei.measurementservice.repository;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.mpei.measurementservice.model.DataItemEntity;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class JpaRepositoryTest {

    @Autowired
    private JpaRepository repository;

    @Test
    void testSave1(){
        Optional<DataItemEntity> tag = repository.get("tag");
        Assertions.assertTrue(tag.isEmpty());

        repository.save(new DataItemEntity(0, 2.0, "tag"));

         tag = repository.get("tag");
        Assertions.assertTrue(tag.isPresent());
        Assertions.assertEquals(2.0, tag.get().getValue());
    }

    @Test
    void testDelete(){
        repository.save(new DataItemEntity(0, 2.0, "tag"));

        Optional<DataItemEntity> tag = repository.get("tag");
        Assertions.assertTrue(tag.isPresent());
        Assertions.assertEquals(2.0, tag.get().getValue());

        int delete = repository.delete("tag");
        Assertions.assertEquals(1, delete);


        tag = repository.get("tag");
        Assertions.assertTrue(tag.isEmpty());
    }

    @Test
    void testGetAll(){
        List<DataItemEntity> l = repository.getAll();
        Assertions.assertTrue(l.isEmpty());
        repository.save(new DataItemEntity(0, 2.0, "tag"));
        repository.save(new DataItemEntity(0, 3.0, "tag1"));
        repository.save(new DataItemEntity(0, 1.0, "tag2"));

        List<DataItemEntity> l2 = repository.getAll();
        Assertions.assertEquals(3, l2.size());
    }

    @Test
    void testGetById(){
        repository.save(new DataItemEntity(0, 2.0, "tag"));
        Optional<DataItemEntity> tag = repository.get("tag");
        Assertions.assertTrue(tag.isPresent());
        Assertions.assertTrue(tag.get().getId() > 0);
    }



    @BeforeEach
    public void beforeEach(){
        repository.deleteAll();
    }
}