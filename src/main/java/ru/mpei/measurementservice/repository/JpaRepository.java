package ru.mpei.measurementservice.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.mpei.measurementservice.model.DataItemDto;
import ru.mpei.measurementservice.model.DataItemEntity;
import java.util.List;
import java.util.Optional;

@Repository
public class JpaRepository {

    @PersistenceContext
    private EntityManager em;

    @Transactional
    public void save(DataItemEntity dte){
        em.persist(dte);
    }

    @Transactional(readOnly = true)
    public DataItemEntity get(long id){
        return em.find(DataItemEntity.class, id);
    }

    @Transactional(readOnly = true)
    public Optional<DataItemEntity> get(String tag){

        return em.createQuery("select p from DataItemEntity p where p.tag = :tag", DataItemEntity.class)
                .setParameter("tag", tag)
                .getResultStream().findAny();
    }

    @Transactional
    public int delete(String tag){
        int param = em.createQuery("delete from DataItemEntity p where p.tag = :param")
                .setParameter("param", tag)
                .executeUpdate();
        return param;
    }

    @Transactional
    public void deleteAll(){
        em.createQuery("delete from DataItemEntity").executeUpdate();
    }

    public List<DataItemEntity> getAll(){
        return em.createQuery("select p from DataItemEntity p", DataItemEntity.class)
                .getResultList();
    }


}
