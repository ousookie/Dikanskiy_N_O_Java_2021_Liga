package ru.dikanskiy.entities.interfaces;

public interface Product {

    long initId = 0;

    long getId();

    String getName();

    long getPrice();

    String getDescription();

    void setId(long id);

    void setName(String name);

    void setPrice(long price);

    void setDescription(String description);

}
