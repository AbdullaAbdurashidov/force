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
@Table(name="books")
public class Books implements Serializable {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    public Long bookID;

    @Column(name = "subject")
    private String subject;

    @Column(name = "bookTitle")
    private String bookTitle;

    @Column(name = "bookAuthor")
    private String bookAuthor;

    @Column(name = "bookISBN")
    private String bookISBN;

    @Column(name = "publishDate")
    private String publishDate;

    @Column(name = "reserved")
    private int reserved;

    @Column(name = "borrowed")
    private int borrowed;

    @Column(name = "issueDate")
    private String issueDate;

    @Column(name = "dueDate")
    private String dueDate;

    @Column(name = "borrowedBy")
    private int borrowedBy;

    @Column(name = "reservedBy")
    private int reservedBy;

    @Column(name = "details")
    private String details;
}
