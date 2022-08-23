package com.example.soccermanagerapi.entity;

import com.example.soccermanagerapi.entity.enums.PlayerPositionEnum;
import com.example.soccermanagerapi.entity.template.AbsLongEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Player extends AbsLongEntity {

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @Column(nullable = false)
    private Integer age;

    private String country;

    @Column(nullable = false)
    private Integer marketValue; //bozor qiymati

    @Enumerated(EnumType.STRING)
    @ElementCollection
    private List<PlayerPositionEnum> playerPositionEnum;



    public void randomAge() {
        int min=18;
        int max =40;
        this.age = (int) Math.floor(Math.random() * (max - min + 1) + min);
        System.out.println(age);
    }



    public Player(String firstName, String lastName, String country) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.country = country;
    }
}
