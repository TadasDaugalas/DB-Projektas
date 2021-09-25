package examination.services;

import examination.entity.Exam;
import examination.entity.User;


import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.util.stream.Collectors;

public class MenuService {
    private MenuState menuState = MenuState.ANONYMOUS;
    private User user = null;
    private Optional<Long> examId = Optional.empty();
    private final LoginService loginService;
    private final QuestionService questionService;
    private final ExamService examService;

    public MenuService() {
        loginService = new LoginService();
        examService = new ExamService();
        questionService= new QuestionService();
    }

    Scanner sc = new Scanner(System.in);

    public void view() {
        boolean stop = false;
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
                        case 1 -> menuState = MenuState.LOGIN;
                        case 2 -> menuState = MenuState.REGISTER;
                    }
                }

                case LOGIN -> {
                    System.out.println("Enter username");
                    String userName = sc.nextLine();
                    System.out.println("Enter password");
                    String password = sc.nextLine();

                    user = loginService.login(userName, password);

                    if (user != null) {
                        menuState = MenuState.EXAM_MENU;
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
                        menuState = MenuState.EXAM_MENU;
                    }
                }
                case EXAM_MENU -> {
                    if (user.isAdmin()) {
                        adminExamMenu();
                    } else {
                        System.out.println("Student in progress");
                        menuState = MenuState.ANONYMOUS;
                    }
                }
                case ADD_EXAM -> {
                    System.out.println("Enter name");
                    examService.createExam(sc.nextLine());
                    menuState = MenuState.EXAM_MENU;
                }
                case EDIT_EXAM -> {
                    List<Exam> list = examService.getList();
                    if (list.size() == 0) {
                        System.out.println("Exam list is empty");
                        menuState = MenuState.EXAM_MENU;
                    } else {
                        list.forEach(e -> {
                            System.out.println(e.getId() + "\t" + e.getName());
                        });

                        Long examId = Long.parseLong(sc.nextLine());
                        List<Long> examIds = list.stream().map(exam -> exam.getId()).collect(Collectors.toList());
                        if (examIds.contains(examId)) {
                            this.examId = Optional.of(examId);
                            menuState = MenuState.EDIT_EXAM_ITEM;
                        } else {
                            System.out.println("Invalid exam id");
                        }
                    }

                }
                case EDIT_EXAM_ITEM -> {
                    System.out.println("Edit exam: " + this.examId.get());
                    questionMenu();
                }
                case ADD_QUESTION -> {
                    System.out.println("Enter question text");
                    String questionText = sc.nextLine();
                    System.out.println("Enter first answer");
                    String answer1= sc.nextLine();
                    System.out.println("Enter second answer");
                    String answer2= sc.nextLine();
                    System.out.println("Enter third answer");
                    String answer3= sc.nextLine();
                    System.out.println("Enter correct answer");
                    int correctAnsw=Integer.parseInt(sc.nextLine());

                    questionService.createQuestion(examId.get(),questionText,answer1,answer2,answer3,correctAnsw);

                    menuState=MenuState.EXAM_MENU;
                }
                case UPDATE_QUESTION -> {
                    System.out.println("Enter Question ID");
                    Long id=Long.parseLong(sc.nextLine());
                    System.out.println("Enter question text");
                    String questionText = sc.nextLine();
                    System.out.println("Enter first answer");
                    String answer1= sc.nextLine();
                    System.out.println("Enter second answer");
                    String answer2= sc.nextLine();
                    System.out.println("Enter third answer");
                    String answer3= sc.nextLine();
                    System.out.println("Enter correct answer");
                    int correctAnsw=Integer.parseInt(sc.nextLine());
                    questionService.updateQuestion(id,questionText,answer1,answer2,answer3,correctAnsw);
                    menuState=MenuState.EXAM_MENU;
                }

            }
        }
    }
    private void questionMenu(){
        System.out.println("1-Add question");
        System.out.println("2-Edit question");
        System.out.println("0-Back");
        int command = enterCommand(2);
        if (command == 0) {
            user = null;
            menuState = MenuState.ANONYMOUS;
            return;
        }
        switch (command) {
            case 1 -> menuState = MenuState.ADD_QUESTION;
            case 2 -> menuState = MenuState.UPDATE_QUESTION;
        }
    }

    private void adminExamMenu() {
        System.out.println("1-Add exam");
        System.out.println("2-Edit exam");
        System.out.println("0-Log out");
        int command = enterCommand(2);
        if (command == 0) {
            user = null;
            menuState = MenuState.ANONYMOUS;
            return;
        }
        switch (command) {
            case 1 -> menuState = MenuState.ADD_EXAM;
            case 2 -> menuState = MenuState.EDIT_EXAM;
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
        EXAM_MENU,
        ADD_EXAM,
        EDIT_EXAM,
        EDIT_EXAM_ITEM,
        ADD_QUESTION,
        UPDATE_QUESTION
    }
}

