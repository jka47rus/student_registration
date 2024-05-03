package com.example.student_registration;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

import java.util.Scanner;

@RequiredArgsConstructor
public class CreateStudent {

    private final Registration registration;

    @EventListener(ApplicationReadyEvent.class)
    private void registerStudent() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите имя, фамилия, возраст студента через пробел: ");
        String text = scanner.nextLine();
        String[] values = text.split("\\s");
        if (values.length != 3) {
            System.out.println("Ошибка при попытке создания студента!");
        } else {
            registration.crateStudent(values[0].trim(), values[1].trim(), Integer.valueOf(values[2].trim()));
        }
    }


}
