package ru.dikanskiy.entities.implementations.product;

import java.util.Objects;

public class Product {

    private long id;
    private static long currentId = 0;
    private String name;
    private long price;
    private String description;

    public Product() {

    }

    public Product(String name, long price, String description) {
        currentId++;
        this.id = currentId;
        this.name = name;
        this.price = price;
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Product that = (Product) o;
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

    public long getId() {
        return id;
    }


    public String getName() {
        return name;
    }


    public long getPrice() {
        return price;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return "ProductImpl{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", description='" + description + '\'' +
                '}';
    }

}

