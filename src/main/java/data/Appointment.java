package data;

import jfxtras.scene.control.agenda.Agenda;

import java.time.LocalDateTime;

public class Appointment extends Agenda.AppointmentImplLocal {
    private int id;
    private Subject subject;
    private Person teacher;
    public Appointment(int id, LocalDateTime startdatetime, LocalDateTime enddatetime, String summary, String descr, Subject subject, Person teacher) {
        this.id = id;
        this.subject = subject;
        this.teacher = teacher;
        this.withStartLocalDateTime(startdatetime)
                .withEndLocalDateTime(enddatetime)
                .withSummary(summary)
                .withDescription(descr);

    }

    public Appointment( LocalDateTime startdatetime, LocalDateTime enddatetime, String summary, String descr, Subject subject, Person teacher) {
        this.subject = subject;
        this.teacher = teacher;
        this.withStartLocalDateTime(startdatetime)
                .withEndLocalDateTime(enddatetime)
                .withSummary(summary)
                .withDescription(descr);

    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    public Person getTeacher() {
        return teacher;
    }

    public void setTeacher(Person teacher) {
        this.teacher = teacher;
    }
}
