package com.example.student_registration;

import com.example.student_registration.event.EventHolder;
import com.example.student_registration.model.Student;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

import java.text.MessageFormat;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@ShellComponent
public class Registration {
    private final ApplicationEventPublisher publisher;
    private final Map<Integer, Student> students = new HashMap<>();

    public Registration(ApplicationEventPublisher publisher) {
        this.publisher = publisher;
    }

    @ShellMethod(key = "i")
    public void init(@ShellOption(value = "n") String name,
                       @ShellOption(value = "l") String lastName,
                       @ShellOption(value = "a") Integer age) {
//        System.out.println("Введите данные студента в формате: init --n Name --l Second name --a age");
        Student student = Student.builder()
                .name(name)
                .lastName(lastName)
                .age(age)
                .build();
        Integer id = idGenerator();
        students.put(id, student);
        publisher.publishEvent(new EventHolder(this, student, 0));
    }


    private Integer idGenerator() {
        Integer id = 0;
        if (students.isEmpty()) {
            id = 1;
        } else {
            id = Collections.max(students.keySet()) + 1;
        }
        return id;
    }

    @ShellMethod(key = "sh")
    public void showAllStudents() {
        if (students.isEmpty()) {
            System.out.println("Список студентов пустой");
        } else {
            students.entrySet().forEach(entry -> {
                System.out.println(entry.getValue().toString() + ", id №" + entry.getKey());
            });
        }
    }

    @ShellMethod(key = "d")
    public void deleteStudent(@ShellOption(value = "id") int id) {
        if (!students.containsKey(id)) {
            System.out.printf("Студент с id №%s отсутствует в списке!\n", id);
        } else {
            publisher.publishEvent(new EventHolder(this, students.get(id), 1));
            students.remove(id);
        }
    }

    @ShellMethod(key = "c")
    public void clear() {
        if (students.isEmpty()) {
            System.out.println("Список студентов пуст!\n");
        } else {
            students.clear();
            System.out.println("Список студентов очищен!\n");
        }
    }

    public String crateStudent(String name,
                               String lastName,
                               Integer age) {
        Student student = Student.builder()
                .name(name)
                .lastName(lastName)
                .age(age)
                .build();

        Integer id = idGenerator();
        students.put(id, student);
        return MessageFormat.format("Студент: {0} {1} {2}, id №{3}  записан!",
                student.getName(), student.getLastName(), student.getAge(), id);
    }

}
