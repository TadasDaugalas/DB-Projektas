package Services.Exam;

import Services.ConnectionToDb;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Scanner;

public class ExamService {
    ConnectionToDb connectionToDb=new ConnectionToDb();
    Connection connection= connectionToDb.connectionDB();
    public void createExam(Scanner sc){
    try (PreparedStatement preparedStatement =connection.prepareStatement("INSERT INTO exam(examname,createdate)" +
            "VALUES (?,?)")){
        System.out.println("Insert exam name");
        preparedStatement.setString(1, sc.nextLine());
        java.sql.Timestamp date = new java.sql.Timestamp(new java.util.Date().getTime());
        preparedStatement.setTimestamp(2,date);
        preparedStatement.execute();
    }catch (Exception e){
        System.out.println(e);
    }
    }
    public void setQuestion(Scanner sc){
        try (PreparedStatement preparedStatement =connection.prepareStatement("INSERT INTO question(examid,question,answer1," +
                "answer2,answer3) VALUES(?,?,?,?,?)")){
            System.out.println("Insert exam id ");
            preparedStatement.setInt(1,Integer.parseInt(sc.nextLine()));
            System.out.println("Insert question");
            preparedStatement.setString(2,sc.nextLine());
            System.out.println("Insert first answer");
            preparedStatement.setString(3,sc.nextLine());
            System.out.println("Insert second answer");
            preparedStatement.setString(4,sc.nextLine());
            System.out.println("Insert third answer");
            preparedStatement.setString(5,sc.nextLine());
            preparedStatement.execute();

        }catch (Exception e){
            System.out.println(e);
        }
    }
}
