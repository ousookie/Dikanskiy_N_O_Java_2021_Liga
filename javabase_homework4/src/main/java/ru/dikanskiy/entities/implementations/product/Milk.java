package ru.dikanskiy.entities.implementations.product;

import ru.dikanskiy.entities.implementations.base.BaseProduct;

public class Milk extends BaseProduct {

    public Milk() {
        super();
    }

    public Milk(String name, long price, String description) {
        super(name, price, description);
    }

    @Override
    public String toString() {
        return "Milk{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", description='" + description + '\'' +
                '}';
    }

}
