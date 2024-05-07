package com.example.student_registration.event;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class EventHolderListener {
    @EventListener
    public void listen(EventHolder eventHolder) {
        if (eventHolder.getNumber() == 0) {
            initStudent(eventHolder);
        } else {
            deleteStudent(eventHolder);
        }
    }


    private void initStudent(EventHolder eventHolder) {
        System.out.printf("Студент: %s %s записан!%n",
                eventHolder.getStudent().getName(), eventHolder.getStudent().getLastName());
    }

    private void deleteStudent(EventHolder eventHolder) {
        System.out.printf("Студент: %s %s удален!%n",
                eventHolder.getStudent().getName(), eventHolder.getStudent().getLastName());
    }

}
