package com.example.Library.Management.System.Service;

import com.example.Library.Management.System.Entites.Book;
import com.example.Library.Management.System.Entites.LibraryCard;
import com.example.Library.Management.System.Entites.Transaction;
import com.example.Library.Management.System.Enums.CardStatus;
import com.example.Library.Management.System.Enums.TransactionStatus;
import com.example.Library.Management.System.Exceptions.*;
import com.example.Library.Management.System.Repository.BookRepository;
import com.example.Library.Management.System.Repository.CardRepository;
import com.example.Library.Management.System.Repository.TransactionRepository;
import org.apache.tomcat.jni.Library;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

@Service
public class TransactionService {
    @Autowired
    private CardRepository cardRepository;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private TransactionRepository transactionRepository;
    private static final int MAX_LIMIT=3;
    private static final int findAmount=5;
    public String issueBook(Integer cardId, Integer bookId) throws Exception{
        Transaction transaction=new Transaction();
        transaction.setTransactionStatus(TransactionStatus.PENDING);
        Optional<LibraryCard> optionalLibraryCard=cardRepository.findById(cardId);

        if(!optionalLibraryCard.isPresent()){
            throw new CardNotFoundException("card is not present in db");
        }
        LibraryCard libraryCard=optionalLibraryCard.get();

        if(!libraryCard.getCardStatus().equals(CardStatus.ACTIVE)){
            throw new InvalidCardStatusException("card status is not in active ");
        }

        Optional<Book> optionalBook=bookRepository.findById(bookId);

        if(!optionalBook.isPresent()){
            throw new BookNotFound("book not found");
        }
        Book book=optionalBook.get();
        if(!book.isAvailable()){
            throw new BookIsNotAvailable("book is not available");
        }

        if(libraryCard.getNoofbooksissued()==MAX_LIMIT){
            throw new MaxBooksIssuedException("maximum limit exceeded");
        }

        transaction.setTransactionStatus(TransactionStatus.ISSUED);
        libraryCard.setNoofbooksissued(libraryCard.getNoofbooksissued()+1);

        book.setAvailable(false);

        transaction.setBook(book);
        transaction.setLibraryCard(libraryCard);


        book.getTransactionList().add(transaction);
        libraryCard.getTransactionList().add(transaction);

        transactionRepository.save(transaction);

        return "transaction is issued";

    }

    public String returnBook(Integer cardId, Integer bookId)throws Exception {
        Optional<LibraryCard> optionalLibraryCard=cardRepository.findById(cardId);

        if(!optionalLibraryCard.isPresent()){
            throw new CardNotFoundException("card is not present in db");
        }
        LibraryCard libraryCard=optionalLibraryCard.get();

        if(!libraryCard.getCardStatus().equals(CardStatus.ACTIVE)){
            throw new InvalidCardStatusException("card status is not in active ");
        }

        Optional<Book> optionalBook=bookRepository.findById(bookId);

        if(!optionalBook.isPresent()){
            throw new BookNotFound("book not found");
        }
        Book book=optionalBook.get();
        Transaction transaction=transactionRepository.findTransactionByBookAndLibraryCardAndTransactionStatus  (book,libraryCard,TransactionStatus.ISSUED);
        Date issueDate=transaction.getCreatedOn();

        transaction.setReturnDate(new Date());

        long timeMilliSecounds=Math.abs(System.currentTimeMillis()-issueDate.getTime());
        long days= TimeUnit.DAYS.convert(timeMilliSecounds,TimeUnit.MILLISECONDS);

        int fine=0;
        if(days>15){
            fine= Math.toIntExact((days - 15) * findAmount);
        }
        transaction.setFine(fine);
        transaction.setTransactionStatus(TransactionStatus.COMPLETED);
        transaction.setBook(book);
        transaction.setLibraryCard(libraryCard);

        book.setAvailable(true);

        libraryCard.setNoofbooksissued(libraryCard.getNoofbooksissued()-1);

        book.getTransactionList().add(transaction);
        libraryCard.getTransactionList().add(transaction);



        transactionRepository.save(transaction);


        return "book is returned";
    }
}
