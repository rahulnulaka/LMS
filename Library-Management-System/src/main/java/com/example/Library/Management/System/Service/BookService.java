package com.example.Library.Management.System.Service;

import com.example.Library.Management.System.Entites.Author;
import com.example.Library.Management.System.Entites.Book;
import com.example.Library.Management.System.Exceptions.AuthorNotFoundException;
import com.example.Library.Management.System.Repository.AuthorRepository;
import com.example.Library.Management.System.Repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BookService {
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private AuthorRepository authorRepository;
    public String addBook(Book book, Integer authorid) throws Exception{
        Optional<Author> optionalAuthor=authorRepository.findById(authorid);

        if(!optionalAuthor.isPresent()){
            throw new AuthorNotFoundException("authorid is invalid");
        }

        Author author=optionalAuthor.get();

        author.getBooklist().add(book);

        book.setAuthor(author);

        authorRepository.save(author);
        return "Book saved to DB";
    }
}
