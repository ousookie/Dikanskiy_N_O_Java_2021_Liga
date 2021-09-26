package ru.dikanskiy.entities.implementations.product;

import ru.dikanskiy.entities.implementations.base.BaseProduct;

public class Cheese extends BaseProduct {

    public Cheese() {
        super();
    }

    public Cheese(String name, long price, String description) {
        super(name, price, description);
    }

    @Override
    public String toString() {
        return "Cheese{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", description='" + description + '\'' +
                '}';
    }

}
