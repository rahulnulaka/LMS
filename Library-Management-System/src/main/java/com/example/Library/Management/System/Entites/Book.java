package com.example.Library.Management.System.Entites;

import com.example.Library.Management.System.Enums.Genre;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer bookid;

    private String bookname;

    private int noofpages;

    @Enumerated(value= EnumType.STRING)
    private Genre genre;

    private double rating;

    private boolean available;

    @ManyToOne
    @JoinColumn
    private Author author;


    @OneToMany(mappedBy = "book",cascade = CascadeType.ALL)
    List<Transaction> transactionList=new ArrayList<>();

}
