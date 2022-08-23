package com.example.soccermanagerapi.payload;

import lombok.Data;

import javax.persistence.Column;

@Data
public class PlayerDto {

    private String firstName;

    private String lastName;

    private String country;

    private Integer marketValue;

    private Long teamId;
}
