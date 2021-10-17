package com.code.force.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsersReport {

    private Long userID;

    private String firstName;

    private String lastName;

    private Integer presentDays;

    private Integer missedDays;

    private Integer allHours;

    public void display(){
        System.out.println();
        System.out.print(userID+"  ");
        System.out.print(firstName+"  ");
        System.out.print(lastName+"  ");
        System.out.print("Present days: "+presentDays+"  ");
        System.out.print("Missed days: "+missedDays+"  ");
        System.out.print("All hours: "+allHours);
        System.out.println();
    }
}
