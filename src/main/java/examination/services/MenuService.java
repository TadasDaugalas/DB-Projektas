package examination.services;

import examination.entity.User;

import java.util.Optional;
import java.util.Scanner;

public class MenuService {
    private MenuState menuState = MenuState.ANONYMOUS;
    private LoginService loginService;

    public MenuService() {
        loginService = new LoginService();
    }

    Scanner sc = new Scanner(System.in);

    public void view() {
        boolean stop = false;
        User user = null;
        while (!stop) {
            switch (menuState) {
                case ANONYMOUS -> {
                    System.out.println("1-Login");
                    System.out.println("2-Registration");
                    System.out.println("0-Exit");
                    int command = enterCommand(2);
                    if (command == 0) {
                        System.out.println("Bye bye");
                        stop = true;
                    }
                    switch (command) {
                        case 1:
                            menuState = MenuState.LOGIN;
                            break;
                        case 2:
                            menuState = MenuState.REGISTER;
                            break;
                    }
                }

                case LOGIN -> {
                    System.out.println("Enter username");
                    String userName = sc.nextLine();
                    System.out.println("Enter password");
                    String password = sc.nextLine();

                    user = loginService.login(userName, password);

                    if (user != null) {
                        menuState = MenuState.LOGGED_IN;
                    } else {
                        System.out.println("Invalid username or password");
                        menuState = MenuState.ANONYMOUS;
                    }
                }
                case REGISTER -> {
                    System.out.println("Enter username");
                    String userName = sc.nextLine();
                    System.out.println("Enter password");
                    String password = sc.nextLine();
                    System.out.println("Enter name");
                    String name = sc.nextLine();
                    System.out.println("Enter surname");
                    String surName = sc.nextLine();
                    user = loginService.register(userName, password, name, surName);
                    if (user != null) {
                        menuState = MenuState.LOGGED_IN;
                    }
                }
                case LOGGED_IN -> {
                    System.out.println("Hello");
                    menuState=MenuState.ANONYMOUS;
                }
            }
        }
    }

    private int enterCommand(int max) {
        boolean repeat = true;
        int number = 0;
        do {
            String a = sc.nextLine();

            try {
                number = Integer.parseInt(a);
            } catch (NumberFormatException e) {
                System.out.println("Error, please enter number");
                continue;
            }
            repeat = !(0 <= number && number <= max);
            if (repeat) {
                System.out.println("Error, unknown menu item");
            }
        } while (repeat);
        return number;
    }

    private enum MenuState {
        ANONYMOUS,
        LOGIN,
        REGISTER,
        LOGGED_IN
    }
}

