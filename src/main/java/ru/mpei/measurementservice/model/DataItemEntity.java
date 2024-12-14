package ru.mpei.measurementservice.model;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "Measurements")
public class DataItemEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column
    private double value;
    @Column
    private String tag;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DataItemEntity that = (DataItemEntity) o;
        return id == that.id && Double.compare(value, that.value) == 0 && Objects.equals(tag, that.tag);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, value, tag);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }
}
