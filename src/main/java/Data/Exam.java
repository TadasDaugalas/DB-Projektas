package Data;

import java.sql.Date;
public class Exam {
    private String examName;
    private int examId;
    private Date date;
    private int count;

    public Exam(String examName, int examId, Date date, int count) {
        this.examName = examName;
        this.examId = examId;
        this.date = date;
        this.count = count;
    }

    public String getExamName() {
        return examName;
    }

    public void setExamName(String examName) {
        this.examName = examName;
    }

    public int getExamId() {
        return examId;
    }

    public void setExamId(int examId) {
        this.examId = examId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
