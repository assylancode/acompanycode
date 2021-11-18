package com.acode.acodecompany.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@Configuration
public class StudentConfig {
    @Bean
    CommandLineRunner commandLineRunner(StudentRepository studentRepository){
        return args -> {
            Student vipStudent = new Student("VipStudent",
                    "VipStudent@gmail.com",
                    LocalDate.of(2000, Month.NOVEMBER,9));
            Student looserStudent = new Student("LooserStudent",
                    "LooserStudent@gmail.com",
                    LocalDate.of(1998, Month.NOVEMBER,9));
            studentRepository.saveAll(List.of(vipStudent,looserStudent));
        };
    }
}
