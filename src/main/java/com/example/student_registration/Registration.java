package com.example.student_registration;

import com.example.student_registration.model.Student;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import org.springframework.stereotype.Component;

import java.text.MessageFormat;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@ShellComponent
@Component
public class Registration {
    private final Map<Integer, Student> students = new HashMap<>();

    @ShellMethod(key = "i")
    public String init(@ShellOption(value = "n") String name,
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
        return MessageFormat.format("Студент: {0} {1} {2}, id №{3}  записан!",
                student.getName(), student.getLastName(), student.getAge(), id);

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
            students.remove(id);
            System.out.format("Студент с id №%s удален!\n", id);
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
