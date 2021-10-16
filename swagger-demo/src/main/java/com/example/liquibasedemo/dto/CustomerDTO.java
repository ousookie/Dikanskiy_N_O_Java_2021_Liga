package com.example.liquibasedemo.dto;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;

@Data
@AllArgsConstructor
@ApiModel(description = "CustomerDTO entity")
public class CustomerDTO {

    private UUID id;

    private String name;

}
