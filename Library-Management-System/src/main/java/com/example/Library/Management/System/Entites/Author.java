package com.example.Library.Management.System.Entites;

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
public class Author {

    @Id
    private Integer authorid;

    private String authorname;

    private double rating;

    @OneToMany(mappedBy = "author"  , cascade = CascadeType.ALL)
    private List<Book> booklist=new ArrayList<>();
}
