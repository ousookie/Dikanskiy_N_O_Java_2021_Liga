package com.example.liquibasedemo.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.math.BigDecimal;
import java.util.UUID;


@Entity
@Getter
@Setter
@ApiModel(description = "Customer entity")
public class Customer extends NamedEntity {

    @Column
    @ApiModelProperty("Customer's rating")
    private Long rating;

    /*
     * @Column(length = 1024)
     * private String address;
     * */

}

