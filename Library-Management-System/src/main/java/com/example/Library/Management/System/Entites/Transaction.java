package com.example.Library.Management.System.Entites;

import com.example.Library.Management.System.Enums.TransactionStatus;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;

@Entity
@Table
@FieldDefaults(level=AccessLevel.PRIVATE)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Transaction {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    Integer transactionId;
    @Enumerated(value= EnumType.STRING)
    TransactionStatus transactionStatus;
    Date returnDate;

    int fine;
    @CreationTimestamp
    Date createdOn;
    @UpdateTimestamp
    Date lastModifiedOn;

    @ManyToOne
    @JoinColumn
    Book book;


    @ManyToOne
    @JoinColumn
    LibraryCard libraryCard;


}
