package com.acode.acodecompany.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<Student> getStudents(){
        return studentRepository.findAll();
    }

    public void addNewStudent(Student student) {
        Optional<Student> studentOptional = studentRepository.findStudentByEmail(student.getEmail());
        if(studentOptional.isPresent()){
            throw new IllegalStateException("email is not free!");
        }
        studentRepository.save(student);
    }

    public void deleteStudent(Long studentId) {
        boolean exist =studentRepository.existsById(studentId);
        if(!exist){
            throw new IllegalStateException(
                    "student with id "+studentId+"does not exists!");
        }
        studentRepository.deleteById(studentId);

    }
    @Transactional
    public void updateStudent(Long studentId, String name, String email) {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(()->new IllegalStateException("student by id "+studentId+" does not find!"));
        if(!name.isEmpty() && name.length()>0
                && !name.equals(student.getName())){
            student.setName(name);
        }

        if(!email.isEmpty() && email.length()>0 && !email.equals(student.getEmail())){
            if(studentRepository.findStudentByEmail(email).isPresent()){
                throw new IllegalStateException("email "+email+"is not unique!");
            }
            student.setEmail(email);

        }


    }
}
