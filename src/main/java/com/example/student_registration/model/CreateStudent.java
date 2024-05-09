package com.example.student_registration.model;

import com.example.student_registration.Registration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
@ConditionalOnProperty("app.create-student.enabled")
public class CreateStudent {

    @EventListener(ApplicationStartedEvent.class)
    public void crateStudent() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите количество студентов, которых хотите создать: ");
        int quantity = scanner.nextInt();

        scanner = new Scanner(System.in);
        for (int i = 0; i < quantity; i++) {

            System.out.println("Введите имя, фамилия, возраст студента через пробел: ");
            String text = scanner.nextLine();


            String[] values = text.split("\\s");
            if (values.length != 3) {
                System.out.println("Ошибка при попытке создания студента!");
            } else {
                Student student = Student.builder()
                        .name(values[0].trim())
                        .lastName(values[1].trim())
                        .age(Integer.valueOf(values[2].trim()))
                        .build();

                Integer id = Registration.idGenerator();
                Registration.students.put(id, student);
                System.out.printf("Студент: %s %s записан!%n",
                        student.getName(), student.getLastName());

            }


        }
    }


}
