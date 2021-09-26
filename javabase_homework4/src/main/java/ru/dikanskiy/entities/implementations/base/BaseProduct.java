package ru.dikanskiy.entities.implementations.base;

import ru.dikanskiy.entities.interfaces.Product;

import java.util.Objects;

abstract public class BaseProduct implements Product {

    protected long id;
    protected static long currentId = initId;
    protected String name;
    protected long price;
    protected String description;

    public BaseProduct() {

    }

    public BaseProduct(String name, long price, String description) {
        currentId++;
        this.id = currentId;
        this.name = name;
        this.price = price;
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BaseProduct that = (BaseProduct) o;
        return id == that.id && price == that.price && Objects.equals(name, that.name) && Objects.equals(description, that.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, price, description);
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(long price) {
        this.price = price;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public long getId() {
        return id;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public long getPrice() {
        return price;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public abstract String toString();

}
