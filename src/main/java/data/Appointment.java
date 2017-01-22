package data;

import jfxtras.scene.control.agenda.Agenda;

import java.time.LocalDateTime;
/**
 * Addition to Agenda.AppointmentImplLocal to save custom values
 *
 * @author Sandro Guerotto
 * @since 14.01.2017
 * @version 0.1
 */
public class Appointment extends Agenda.AppointmentImplLocal {
	
    private int id;
    private Subject subject;
    private Person teacher;
    private String type;
    /**
     * 
     * @param id unique integer key
     * @param startdatetime start date and time of appointment
     * @param enddatetime end date and time of appointment
     * @param summary short description or title
     * @param descr  long description / additional text
     * @param subject type of lesson 
     * @param teacher who created this appointment
     * @param type what type of appointment News / Homework etc..
     */
    public Appointment(int id, LocalDateTime startdatetime, LocalDateTime enddatetime, String summary, String descr, Subject subject, Person teacher, String type) {
        this.id = id;
        this.subject = subject;
        this.teacher = teacher;
        this.type = type;
        this.withStartLocalDateTime(startdatetime)
                .withEndLocalDateTime(enddatetime)
                .withSummary(summary)
                .withDescription(descr);

    }
    
    /**
     * 
     * @param startdatetime start date and time of appointment
     * @param enddatetime end date and time of appointment
     * @param summary short description or title
     * @param descr  long description / additional text
     * @param subject type of lesson 
     * @param teacher who created this appointment
     * @param type what type of appointment News / Homework etc..
     */
    public Appointment( LocalDateTime startdatetime, LocalDateTime enddatetime, String summary, String descr, Subject subject, Person teacher, String type) {
        this.subject = subject;
        this.teacher = teacher;
        this.type = type;
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

    public String getType() {
        return type;
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
