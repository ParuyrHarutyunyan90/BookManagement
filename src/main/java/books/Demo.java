package books;

import books.commands.Commands;
import books.exception.AuthorNotFoundException;
import books.model.*;
import books.storage.AuthorStorage;
import books.storage.BookStorage;
import books.storage.UserStorage;

import java.util.Scanner;

public class Demo implements Commands {
    private static Scanner scanner = new Scanner(System.in);
    private static BookStorage bookStorage = new BookStorage();
    private static AuthorStorage authorStorage = new AuthorStorage();
    private static UserStorage userStorage = new UserStorage();


    public static void main(String[] args) {

        Author author1 = new Author("aaa", "bbb", "ccc", Gender.MALE);
        authorStorage.add(author1);
        Author author2 = new Author("bbb", "ddd", "eee", Gender.FEMALE);
        authorStorage.add(author2);
        Author author3 = new Author("ccc", "fff", "xxx", Gender.MALE);
        authorStorage.add(author3);

        bookStorage.add(new Book("qqq", author1, 416, 3, "drama"));
        bookStorage.add(new Book("www", author2, 124, 4, "all"));
        bookStorage.add(new Book("zzz", author3, 211, 2, "comedy"));

        userStorage.add(new User("admin", "admin", "admin@mail.com", "admin", Role.ADMIN));


        boolean run = true;
        while (run) {

            Commands.printLoginCommands();

            int command;
            try {
                command = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                command = -1;
            }
            switch (command) {
                case EXIT:
                    run = false;
                    break;
                case LOGIN:
                    login();
                    break;
                case REGISTER:
                    register();
                    break;
                default:
                    System.out.println("invalid command, Please try again");
            }
        }

    }

    private static void login() {
        System.out.println("Please input email, password");
        String emailPasswordStr = scanner.nextLine();
        String[] emailPassword = emailPasswordStr.split(",");
        User user = userStorage.getUserByEmail(emailPassword[0]);
        if (user == null) {
            System.out.println("User does not exists !");
        } else if (!user.getPassword().equals(emailPassword[1])) {
            System.out.println("Password is wrong");


        } else {
            if (user.getRole() == Role.ADMIN) {
                adminLogin();
            } else if (user.getRole() == Role.USER) {
                userLogin();
            }
        }

    }


    private static void register() {
        System.out.println("Please input name, surname, email, password,");
        String userDataStr = scanner.nextLine();
        String[] userData = userDataStr.split(",");
        if (userData.length < 4) {
            System.out.println("Please input correct data");
        } else {
            if (userStorage.getUserByEmail(userData[2]) == null) {
                User user = new User();
                user.setName(userData[0]);
                user.setSurName(userData[1]);
                user.setEmail(userData[2]);
                user.setPassword(userData[3]);
                user.setRole(Role.USER);
                userStorage.add(user);
                System.out.println("User registered ! ");
            } else {
                System.out.println("User with" + userData[2] + "already exists");
            }
        }
    }

    private static void userLogin() {
        boolean run = true;
        while (run) {

            Commands.commandlineUser();

            int command;
            try {
                command = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                command = -1;
            }
            switch (command) {
                case LOGOUT:
                    run = false;
                    break;
                case ADD_BOOKS:
                    addBooks();
                    break;
                case PRINT_ALL_BOOKS:
                    bookStorage.print();
                    break;
                case PRINT_BOOKS_BY_AUTHOR_NAME:
                    printByAuthorName();
                    break;
                case PRINT_BOOKS_BY_GENRE:
                    printByGenre();
                    break;
                case PRINT_ALL_AUTHORS:
                    authorStorage.print();
                    break;

                default:
                    System.out.println("invalid command, Please try again");


            }
        }

    }

    private static void adminLogin() {
        boolean run = true;
        while (run) {

            Commands.commandline();

            int command;
            try {
                command = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                command = -1;
            }
            switch (command) {
                case LOGOUT:
                    run = false;
                    break;
                case ADD_BOOKS:
                    addBooks();
                    break;
                case PRINT_ALL_BOOKS:
                    bookStorage.print();
                    break;
                case PRINT_BOOKS_BY_AUTHOR_NAME:
                    printByAuthorName();
                    break;
                case PRINT_BOOKS_BY_GENRE:
                    printByGenre();
                    break;
                case PRINT_BOOKS_BY_PRICE_RANGE:
                    printByPriceRange();
                    break;
                case ADD_AUTHOR:
                    addAuthor();
                    break;
                case PRINT_ALL_AUTHORS:
                    authorStorage.print();
                    break;

                default:
                    System.out.println("invalid command, Please try again");


            }
        }
    }


    private static void addAuthor() {

        System.out.println("Pleae input author name");
        String name = scanner.nextLine();
        System.out.println("Pleae input author surName");
        String surName = scanner.nextLine();
        System.out.println("Please input author email");
        String email = scanner.nextLine();
        System.out.println("Please input author gender (MALE) (FEMALE)");
        try {
            Gender gender = Gender.valueOf(scanner.nextLine().toUpperCase());
            Author author = new Author(name, surName, email, gender);
            authorStorage.add(author);
            System.out.println("Author added");
        } catch (IllegalArgumentException e) {
            System.out.println("incorrect gender: Please input MALE or FEMALE");
            addAuthor();
        }


    }

    private static void printByPriceRange() {
        System.out.println("Please input book min price ");
        try {
            double min = Double.parseDouble(scanner.nextLine());
            System.out.println("Please input book max price ");
            double max = Double.parseDouble(scanner.nextLine());
            bookStorage.printBookByPriceRange(min, max);
        } catch (NumberFormatException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void printByGenre() {
        System.out.println("Please input genre");
        String genre = scanner.nextLine();
        bookStorage.printBookByGenre(genre);
    }

    private static void printByAuthorName() {
        System.out.println("Please input author name");
        String authorName = scanner.nextLine();
        bookStorage.printBookByAuthorName(authorName);
    }

    private static void addBooks() {
        if (authorStorage.getSize() == 0) {
            System.out.println("no Author: please add Author");
            addAuthor();
        } else {
            authorStorage.print();
            System.out.println("Please input book author name index");
            try {
                int index = Integer.parseInt(scanner.nextLine());

                try {
                    Author author = authorStorage.getByIndex(index);


                    System.out.println("Please input book title");
                    String title = scanner.nextLine();
                    System.out.println("Please input book price");
                    String priceStr = scanner.nextLine();
                    System.out.println("Please input book count");
                    String countStr = scanner.nextLine();
                    System.out.println("Please input book genre");
                    String genre = scanner.nextLine();

                    double price = Double.parseDouble(priceStr);
                    int count = Integer.parseInt(countStr);
                    Book book = new Book(title, author, price, count, genre);
                    bookStorage.add(book);
                    System.out.println("Thank you book is added");

                } catch (AuthorNotFoundException e) {
                    System.out.println(e.getMessage());
                }
            } catch (NumberFormatException e) {
                e.getMessage();
            }
        }
    }

}
