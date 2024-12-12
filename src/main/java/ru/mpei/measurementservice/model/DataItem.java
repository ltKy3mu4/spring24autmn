package ru.mpei.measurementservice.model;

import java.util.Objects;

public class DataItem {
    private String tag;
    private double value;

    public DataItem() {
    }

    public DataItem(String tag, double value) {
        this.tag = tag;
        this.value = value;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DataItem dataItem = (DataItem) o;
        return Double.compare(value, dataItem.value) == 0 && Objects.equals(tag, dataItem.tag);
    }

    @Override
    public int hashCode() {
        return Objects.hash(tag, value);
    }
}
