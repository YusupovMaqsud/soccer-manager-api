package com.example.soccermanagerapi.entity;

import com.example.soccermanagerapi.entity.template.AbsLongEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Transfer extends AbsLongEntity {

    private Integer marketValue; //bozor qiymati

    private Integer askedPrice; //so'ralgan narx

    @ManyToOne
    private Team transferredFrom;  //kimdan ko'chirildi

    @ManyToOne
    private Team transferredTo;     //kimga ko'chirildi

    private boolean transferred;        //o'tkazilgan


    public void askedPrices(){
        int min=10;
        int max =100;
        this.askedPrice = (int) Math.floor(Math.random() * (max - min + 5) + min);
        System.out.println(askedPrice);
    }



    public Transfer(Integer marketValue, Team transferredFrom, Team transferredTo, boolean transferred) {
        this.marketValue = marketValue;
        this.transferredFrom = transferredFrom;
        this.transferredTo = transferredTo;
        this.transferred = transferred;
    }
}
