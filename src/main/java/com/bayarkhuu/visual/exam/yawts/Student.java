package com.bayarkhuu.visual.exam.yawts;

/**
 * Student
 *
 * @author Баярхүү.Лув 2022.04.02 19:02
 */
public class Student implements Cloneable {
    private Long id;
    private String firstName;
    private String lastName;
    private double attendance;
    private double project;
    private double writtenQuiz;
    private double prelimExam;
    private double practicalQuiz;

    public Student(Long id, String firstName, String lastName, double attendance, double project, double writtenQuiz, double prelimExam, double practicalQuiz) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.attendance = attendance;
        this.project = project;
        this.writtenQuiz = writtenQuiz;
        this.prelimExam = prelimExam;
        this.practicalQuiz = practicalQuiz;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public double getAttendance() {
        return attendance;
    }

    public void setAttendance(double attendance) {
        this.attendance = attendance;
    }

    public double getProject() {
        return project;
    }

    public void setProject(double project) {
        this.project = project;
    }

    public double getWrittenQuiz() {
        return writtenQuiz;
    }

    public void setWrittenQuiz(double writtenQuiz) {
        this.writtenQuiz = writtenQuiz;
    }

    public double getPrelimExam() {
        return prelimExam;
    }

    public void setPrelimExam(double prelimExam) {
        this.prelimExam = prelimExam;
    }

    public double getPracticalQuiz() {
        return practicalQuiz;
    }

    public void setPracticalQuiz(double practicalQuiz) {
        this.practicalQuiz = practicalQuiz;
    }

    @Override
    public Student clone() {
        try {
            Student student = (Student) super.clone();
            student.id = this.id;
            student.firstName = this.firstName;
            student.lastName = this.lastName;
            student.attendance = this.attendance;
            student.project = this.project;
            student.writtenQuiz = this.writtenQuiz;
            student.prelimExam = this.prelimExam;
            student.practicalQuiz = this.practicalQuiz;
            return student;
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return null;
    }
}
