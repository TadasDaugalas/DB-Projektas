//package Services;
//
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.SQLException;
//
//public class ConnectionToDb {
//    private static final String URl = "postgres.url";
//    private static final String USER = "postgres.user";
//    private static final String PASSWORD = "postgres.password";
//    public Connection connectionDB()  {
//        final AppProperties instance = AppProperties.getInstance();
//        try {
//            Connection connection = DriverManager.getConnection(instance.getProperties(URl), instance.getProperties(USER), instance.getProperties(PASSWORD));
//            System.out.println("Sucsesfully connected");
//            return connection;
//        } catch (SQLException e) {
//            System.out.println("Failed connection to DB " + e);
//        }
//        return  null;
//    }
//}
