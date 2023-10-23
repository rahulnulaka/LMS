package com.example.Library.Management.System.Service;

import com.example.Library.Management.System.Entites.Author;
import com.example.Library.Management.System.Repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AuthorService {
    @Autowired
    AuthorRepository authorRepository;

    public String addAuthor(Author author) {
        authorRepository.save(author);
        return "Author added to Db";
    }

    public List<String> findallauthornames() {
        List<Author> authorlist=authorRepository.findAll();
        List<String> authorname=new ArrayList<>();
        for(Author author:authorlist){
            authorname.add(author.getAuthorname());
        }
        return authorname;
    }

    public Author getauthorbyid(Integer id) throws Exception{
        Optional<Author> author=authorRepository.findById(id);
        if(!author.isPresent()){
            throw new Exception("id id invalid");
        }
        return author.get();
    }
}
