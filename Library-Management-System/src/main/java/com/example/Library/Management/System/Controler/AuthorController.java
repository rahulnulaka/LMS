package com.example.Library.Management.System.Controler;

import com.example.Library.Management.System.Entites.Author;
import com.example.Library.Management.System.Service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/author")
public class AuthorController {
    @Autowired
    AuthorService authorService;

    @PostMapping("/addauthor")
    public ResponseEntity<String> addAuthor(@RequestBody Author author){
        String res=authorService.addAuthor(author);
        return new ResponseEntity<>(res,HttpStatus.OK);
    }
    @GetMapping("/findallauthornames")
    public List<String> findallauthornames(){
        return authorService.findallauthornames();
    }


    @GetMapping("/getId")
    public ResponseEntity getauthorbyid(@RequestParam("id") Integer id){
        try{
            Author author=authorService.getauthorbyid(id);
            return new ResponseEntity(author,HttpStatus.OK);
        }
        catch(Exception e){
            return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

}
