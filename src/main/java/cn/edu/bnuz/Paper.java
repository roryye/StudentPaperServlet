package cn.edu.bnuz;

public class Paper {
    Integer paperId;
    String paperTitle;

    public Paper(String paperTitle, String paperAuthor, String paperSchool) {
        this.paperTitle = paperTitle;
        this.paperAuthor = paperAuthor;
        this.paperSchool = paperSchool;
    }

    String paperAuthor;
    String paperSchool;
    Integer stuNo;

    public Paper() {
    }

    public Paper(Integer paperId, String paperTitle, String paperAuthor, String paperSchool) {
        this.paperId = paperId;
        this.paperTitle = paperTitle;
        this.paperAuthor = paperAuthor;
        this.paperSchool = paperSchool;
    }

    public Integer getPaperId() {
        return paperId;
    }

    public void setPaperId(Integer paperId) {
        this.paperId = paperId;
    }

    public String getPaperTitle() {
        return paperTitle;
    }

    public void setPaperTitle(String paperTitle) {
        this.paperTitle = paperTitle;
    }

    public String getPaperAuthor() {
        return paperAuthor;
    }

    public void setPaperAuthor(String paperAuthor) {
        this.paperAuthor = paperAuthor;
    }

    public String getPaperSchool() {
        return paperSchool;
    }

    public void setPaperSchool(String paperSchool) {
        this.paperSchool = paperSchool;
    }

    public Integer getStuNo() {
        return stuNo;
    }

    public void setStuNo(Integer stuNo) {
        this.stuNo = stuNo;
    }

}
