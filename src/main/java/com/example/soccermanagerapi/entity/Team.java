package com.example.soccermanagerapi.entity;

import com.example.soccermanagerapi.entity.enums.PlayerPositionEnum;
import com.example.soccermanagerapi.entity.template.AbsLongEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Team extends AbsLongEntity {

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String country;


    @Column(nullable = false)
    private Integer extraMoney; //qo'shimcha pul

    @OneToMany
    private List<Player> players;



    public Team(String name, String country, List<Player> players) {
        this.name = name;
        this.country = country;
        this.players = players;
    }

}
