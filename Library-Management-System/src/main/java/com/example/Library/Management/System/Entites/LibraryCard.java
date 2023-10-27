package com.example.Library.Management.System.Entites;

import com.example.Library.Management.System.Enums.CardStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Array;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="librarycard")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LibraryCard {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int cardid;
    @Enumerated(value= EnumType.STRING)
    private CardStatus cardStatus;

    private String nameoncard;

    private int noofbooksissued;
    @OneToOne
    @JoinColumn
    private Student student;


    @OneToMany(mappedBy = "libraryCard",cascade = CascadeType.ALL)
    List<Transaction> transactionList=new ArrayList<>();

}
