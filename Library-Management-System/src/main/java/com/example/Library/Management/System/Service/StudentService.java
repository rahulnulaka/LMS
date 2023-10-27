package com.example.Library.Management.System.Service;

import com.example.Library.Management.System.Entites.Student;
import com.example.Library.Management.System.Repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class StudentService {
    @Autowired
    StudentRepository studentRepository;
    @Autowired
    JavaMailSender javaMailSender;
    public String addStudent(Student student) {
        studentRepository.save(student);
        SimpleMailMessage mailMessage = new SimpleMailMessage();

        String body = "Hi "+student.getName()+" !" +
                "You have successfully registered. You can start issuing the books now.";

        mailMessage.setFrom("swallowuniverse@gmail.com");
        mailMessage.setTo(student.getEmailid());
        mailMessage.setSubject("Welcome To St aloysius high  School's Library !!");
        mailMessage.setText(body);

        javaMailSender.send(mailMessage);
        return "Student saved succesfully";
    }
}
