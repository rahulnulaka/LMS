package com.example.Library.Management.System.Controler;

import com.example.Library.Management.System.Service.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/card")
public class CardController {
    @Autowired
    CardService cardService;


    @PostMapping("/addplaincard")
    public ResponseEntity<String> addplaincard(){
        String res=cardService.addplaincard();
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @PostMapping("/createAssociation")
    public ResponseEntity<String> createAssociation(@RequestParam("studentId") Integer studentId, @RequestParam("cardId")Integer cardId){
        try{
            String res=cardService.createassociation(studentId,cardId);
            return new ResponseEntity<>(res,HttpStatus.OK);
        }
        catch(Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }

    }
}
