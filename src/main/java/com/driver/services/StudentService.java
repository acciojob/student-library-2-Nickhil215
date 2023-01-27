package com.driver.services;

import com.driver.models.Card;
import com.driver.models.CardStatus;
import com.driver.models.Student;
import com.driver.repositories.CardRepository;
import com.driver.repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentService {


    @Autowired
    CardService cardService4;

    @Autowired
    StudentRepository studentRepository4;

    @Autowired
    CardRepository cardRepository;

    public Student getDetailsByEmail(String email){
        Student student = null;
        student=studentRepository4.findByEmailId(email);
        return student;
    }

    public Student getDetailsById(int id){
        Student student = null;
        student=studentRepository4.findById(id).get();
        return student;

    }

//    public void createStudent(Student student){
//        Card card=cardService4.createAndReturn(student);
//        card.setStudent(student);
//        cardRepository.save(card);
//    }

    public void createStudent(Student student){
        if(student != null){
            Card newCard =  cardService4.createAndReturn(student);
        }
    }

    public void updateStudent(Student student){
//        Student updateStudent=studentRepository4.findById(student.getId()).get();
//        updateStudent.setUpdatedOn(student.getUpdatedOn());
//        updateStudent.setCard(student.getCard());
//        updateStudent.setAge(student.getAge());
//        updateStudent.setEmailId(student.getEmailId());
//        updateStudent.setCountry(student.getCountry());
//        updateStudent.setName(student.getName());

        studentRepository4.updateStudentDetails(student);
    }

    public void deleteStudent(int id){
        //Delete student and deactivate corresponding card
        Student student=studentRepository4.findById(id).get();
        if(student!=null){
            cardService4.deactivateCard(id);
            studentRepository4.delete(student);
            }


    }
}
