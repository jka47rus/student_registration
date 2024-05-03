package com.example.student_registration.config;

import com.example.student_registration.CreateStudent;
import com.example.student_registration.Registration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CreateStudentConfiguration {
    @Bean
    public CreateStudent createStudent(Registration registration) {
        return new CreateStudent(registration);

    }
}
