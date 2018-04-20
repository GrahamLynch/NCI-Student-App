package com.example.graha.ncistudentreciever;





public class Attendance {
    private String uid;
    private String sessionDate;
    private String studentNo;
    private String moduleName;
    private String scanDate;
    private String scanTime;
    private String startTime;
    private String endTime;

    public Attendance(){}

    public Attendance(String uid, String sessionDate, String studentNo, String moduleName,
                      String scanDate, String scanTime, String startTime, String endTime) {
        this.uid = uid;
        this.sessionDate = sessionDate;
        this.studentNo = studentNo;
        this.moduleName = moduleName;
        this.scanDate = scanDate;
        this.scanTime = scanTime;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public String getStartTime() {
        return startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public String getUid() {
        return uid;
    }

    public String getStudentNo() {
        return studentNo;
    }

    public String getModuleName() {
        return moduleName;
    }

    public String getScanTime() {
        return scanTime;
    }

    public String getScanDate() {
        return scanDate;
    }

    public String getSessionDate() {
        return sessionDate;
    }
}

