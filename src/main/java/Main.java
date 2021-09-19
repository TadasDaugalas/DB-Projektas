import Services.ConnectionToDb;
import Services.Meniu;

import java.sql.SQLException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws SQLException {
        ConnectionToDb connection = new ConnectionToDb();
        Scanner scanner =new Scanner(System.in);
        Meniu meniu = new Meniu();
//        connection.connectionDB();
        meniu.showMeniu(scanner);
    }
}
