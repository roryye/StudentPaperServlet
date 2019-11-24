package cn.edu.bnuz;

public class Student {

    private int stuNo;
    private String stuName;
    private String stuSchool;

    public Student() {

    }

    public Student(int stuNo, String stuName, String stuSchool) {
        this.stuNo = stuNo;
        this.stuName = stuName;
        this.stuSchool = stuSchool;
    }

    public int getStuNo() {
        return stuNo;
    }

    public void setStuNo(int stuNo) {
        this.stuNo = stuNo;
    }

    public String getStuName() {
        return stuName;
    }

    public void setStuName(String stuName) {
        this.stuName = stuName;
    }

    public String getStuSchool() {
        return stuSchool;
    }

    public void setStuSchool(String stuSchool) {
        this.stuSchool = stuSchool;
    }

}
