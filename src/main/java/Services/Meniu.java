package Services;

import java.util.Scanner;

public class Meniu {
    Scanner sc  = new Scanner(System.in);
    public void showMeniu(){
        System.out.println("1-Login");
        System.out.println("2-Registration");
        System.out.println("0-Log off");

    }
    public void LoginMeniu(){
        System.out.println("Inesrt user name");

        System.out.println("Insert password");
    }

}
