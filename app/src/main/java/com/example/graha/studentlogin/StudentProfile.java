package com.example.graha.studentlogin;

/**
 * Created by graha on 02/03/2018.
 */

public class StudentProfile {
    public String studentName;
    public String studentNumber;
    public String studentEmail;

    public StudentProfile(){

    }



    public StudentProfile(String studentName, String studentNumber, String studentEmail){
        this.studentName = studentName;
        this.studentNumber = studentNumber;
        this.studentEmail = studentEmail;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getStudentNumber() {
        return studentNumber;
    }

    public void setStudentNumber(String studentNumber) {
        this.studentNumber = studentNumber;
    }

    public String getStudentEmail() {
        return studentEmail;
    }

    public void setStudentEmail(String studentEmail) {
        this.studentEmail = studentEmail;
    }
}
