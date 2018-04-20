package com.example.graha.studentlogin;

/**
 * Created by graha on 02/03/2018.
 */

public class StudentProfile {
    public String studentName;
    public String studentNumber;
    public String studentEmail;
    public int attendanceRate;
    public int dataStructures;
    public int interDisciplinaryTeamProject;
    public int softwareEngineering;
    public int dataComms;
    public int business;



    public StudentProfile(){

    }

    public String getStudentName() {
        return studentName;
    }

    public String getStudentNumber() {
        return studentNumber;
    }

    public int getDataStructures() {
        return dataStructures;
    }

    public int getInterDisciplinaryTeamProject() {
        return interDisciplinaryTeamProject;
    }

    public int getSoftwareEngineering() {
        return softwareEngineering;
    }

    public int getDataComms() {
        return dataComms;
    }

    public int getBusiness() {
        return business;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
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


    public int getAttendanceRate() {
        return attendanceRate;
    }

    public void setAttendanceRate(int attendanceRate) {
        this.attendanceRate = attendanceRate;
    }

    public void setDataStructures(int dataStructures) {
        this.dataStructures = dataStructures;
    }

    public void setInterDisciplinaryTeamProject(int interDisciplinaryTeamProject) {
        this.interDisciplinaryTeamProject = interDisciplinaryTeamProject;
    }

    public void setSoftwareEngineering(int softwareEngineering) {
        this.softwareEngineering = softwareEngineering;
    }

    public void setDataComms(int dataComms) {
        this.dataComms = dataComms;
    }

    public void setBusiness(int business) {
        this.business = business;
    }

    public StudentProfile(String studentName, String studentNumber, String studentEmail, int attendanceRate, int dataStructures, int interDisciplinaryTeamProject, int softwareEngineering, int dataComms, int business) {

        this.studentName = studentName;
        this.studentNumber = studentNumber;
        this.studentEmail = studentEmail;
        this.attendanceRate = attendanceRate;
        this.dataStructures = dataStructures;
        this.interDisciplinaryTeamProject = interDisciplinaryTeamProject;
        this.softwareEngineering = softwareEngineering;
        this.dataComms = dataComms;
        this.business = business;
    }
}
