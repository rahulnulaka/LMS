package com.example.Library.Management.System.Repository;

import com.example.Library.Management.System.Entites.Book;
import com.example.Library.Management.System.Entites.LibraryCard;
import com.example.Library.Management.System.Entites.Transaction;
import com.example.Library.Management.System.Enums.TransactionStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction,Integer> {
    Transaction findTransactionByBookAndLibraryCardAndTransactionStatus(Book book,LibraryCard libraryCard,TransactionStatus transactionStatus);
}
