//package Services;
//import Services.Exam.ExamService;
//
//import java.sql.*;
//import java.util.Scanner;
//
//public class Meniu {
//    Scanner sc  = new Scanner(System.in);
//    ConnectionToDb connectionToDb=new ConnectionToDb();
//    Connection connection= connectionToDb.connectionDB();
//    ExamService examService= new ExamService();
//    public void showMeniu(Scanner sc) throws SQLException {
//        while (true) {
//            System.out.println("1-Login for exam");
//            System.out.println("2-Registration");
//            System.out.println("3-Add exam");
//            System.out.println("4-Add Question to exam");
//            System.out.println("0-Log off");
//            switch (sc.nextLine()){
//                case "1":loginMeniu(sc);break;
//                case "2":registration(sc);break;
//                case "3":examService.createExam(sc);break;
//                case "4":examService.setQuestion(sc);break;
//                case "0": connection.close(); return;
//
//            }
//        }
//    }
//    public void loginMeniu(Scanner sc){
//        System.out.println("Inesrt user name");
//        String userName=sc.nextLine();
//        System.out.println("Insert password");
//        String password = sc.nextLine();
//    }
//    public void registration(Scanner sc)  {
//       try( PreparedStatement preparedStatement= connection.prepareStatement("INSERT INTO student (UserName,Name,surname,pasword)\n" +
//                "VALUES (?,?,?,?)")){
//           System.out.println("Insert username");
//           preparedStatement.setString(1,sc.nextLine());
//        System.out.println("insert name");
//        String name = sc.nextLine();
//        preparedStatement.setString(2, name);
//        System.out.println("Insert surname");
//        preparedStatement.setString(3, sc.nextLine());
//        System.out.println("Insert password");
//        preparedStatement.setString(4,sc.nextLine());
//        preparedStatement.execute();
//    }catch (Exception e){
//           System.out.println("Cant create person : " + e );
//       }
//    }
//}
