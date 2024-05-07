package com.example.student_registration.event;

import com.example.student_registration.model.Student;
import lombok.Getter;
import org.springframework.context.ApplicationEvent;

@Getter
public class EventHolder extends ApplicationEvent {

    private final Student student;
    private final int number;

    public EventHolder(Object source, Student student, int number) {
        super(source);
        this.student = student;
        this.number = number;
    }
}
