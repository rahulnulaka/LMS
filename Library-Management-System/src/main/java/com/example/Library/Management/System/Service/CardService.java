package com.example.Library.Management.System.Service;

import com.example.Library.Management.System.Entites.LibraryCard;
import com.example.Library.Management.System.Entites.Student;
import com.example.Library.Management.System.Enums.CardStatus;
import com.example.Library.Management.System.Repository.CardRepository;
import com.example.Library.Management.System.Repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CardService {
    @Autowired
    CardRepository cardRepository;
    @Autowired
    StudentRepository studentRepository;

    public String addplaincard() {
        LibraryCard librarycard=new LibraryCard();

        librarycard.setCardStatus(CardStatus.NEW);

        cardRepository.save(librarycard);
        return "new Card is created with cardid as"+librarycard.getCardid();

    }

    public String createassociation(Integer studentid, Integer cardid)throws Exception {
        Optional<Student> optionalStudent=studentRepository.findById(studentid);

        Optional<LibraryCard> optionalLibraryCard=cardRepository.findById(cardid);
        if(!optionalStudent.isPresent()){
            throw new Exception("student is not present");
        }

        if(!optionalLibraryCard.isPresent()){
            throw new Exception("card is not present");
        }

        Student student=optionalStudent.get();

        LibraryCard card=optionalLibraryCard.get();


        if(card.getStudent()!=null)throw new Exception("Association is already created");


        card.setCardStatus(CardStatus.ACTIVE);

        card.setNameoncard(student.getName());

        card.setStudent(student);


        student.setLibraryCard(card);


        studentRepository.save(student);


        return "association is created for card id"+cardid+ " and student with id as "+studentid;

    }
}
