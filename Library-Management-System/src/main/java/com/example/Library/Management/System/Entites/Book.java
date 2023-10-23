package com.example.Library.Management.System.Entites;

import com.example.Library.Management.System.Enums.Genre;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Book {

    @Id
    private Integer bookid;

    private String bookname;

    private int noofpages;

    @Enumerated(value= EnumType.STRING)
    private Genre genre;

    private double rating;

    @ManyToOne
    @JoinColumn
    private Author author;

}
