package com.example.soccermanagerapi.payload;

import lombok.Data;

@Data
public class TeamDto {

    private String name;

    private String country;

    private Long playerId;
}
