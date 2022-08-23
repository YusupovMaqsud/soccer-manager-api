package com.example.soccermanagerapi.payload;

import com.example.soccermanagerapi.entity.Team;
import lombok.Data;

import javax.persistence.ManyToOne;

@Data
public class TransferDto {

    private Integer marketValue;

    private Integer askedPrice;

    private Long transferredFromId;

    private Long transferredToId;

    private boolean transferred;

}
