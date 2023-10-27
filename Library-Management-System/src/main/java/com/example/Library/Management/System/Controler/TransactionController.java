package com.example.Library.Management.System.Controler;

import com.example.Library.Management.System.Service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/transaction")
public class TransactionController {
    @Autowired
    private TransactionService transactionService;


    @PostMapping("/issueBook/{cardId}/{bookId}")
    public ResponseEntity issueBook(@PathVariable Integer cardId, @PathVariable Integer bookId){
        try{
            String res=transactionService.issueBook(cardId,bookId);
            return new ResponseEntity(res, HttpStatus.OK);
        }
        catch(Exception e){
            return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }
    @PostMapping("/returnBook/{cardId}/{bookId}")
    public ResponseEntity returnBook(@PathVariable Integer cardId, @PathVariable Integer bookId){
        try{
            String res=transactionService.returnBook(cardId,bookId);
            return new ResponseEntity(res, HttpStatus.OK);
        }
        catch(Exception e){
            return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

}
