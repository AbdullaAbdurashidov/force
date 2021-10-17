package com.code.force.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class UsersTime implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userID;

    private String arrivalTime;

    private String leavingTime;

    private String date;

    public UsersTime(Long userID, String arrival, String leaving, String date) {
        this.arrivalTime=arrival;
        this.userID=userID;
        this.leavingTime=leaving;
        this.date=date;
    }
}
