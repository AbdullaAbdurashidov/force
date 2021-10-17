package com.code.force.domain;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Users implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userID;

    private String firstName;

    private String lastName;

    private String userName;

    private String password;

    private int blocked;

    private String role;

    private String imagePath;

    private String documentPath;

    public void display(){
        System.out.println("UserID: "+userID);
        System.out.println("First name: "+firstName);
        System.out.println("Last name: "+lastName);
        System.out.println("User name: "+userName);
        System.out.println("Password: "+password);
        System.out.println("Role: "+role);
        System.out.println("Blocked: "+blocked);
        System.out.println("Image path: "+imagePath);
        System.out.println("Document path: "+documentPath);
    }
}