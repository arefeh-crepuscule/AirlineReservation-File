import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Scanner;

public class Menu extends DetailsAndData{
      public Menu() throws FileNotFoundException {
//        PrimaryData primaryData = new PrimaryData(flights);
//        primaryData.makePrimaryFlights();
    }


    public void printInputMenu() {

        System.out.print("""
                ::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
                           WELCOME TO AIRLINE RESERVATION SYSTEM
                ::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
                ..........................MENU OPTIONS........................
                                
                    <1> Sign in
                    <2> Sign up
                    <3> Exit
                """);
    }
    public void startMenu() throws IOException {
        label:
        while (true) {
            printInputMenu();
            String input = scanner.nextLine();
            switch (input) {
                case "1" -> signIn();
                case "2" ->signUp();
                case "3" -> {
                    break label;
                }
                default -> System.out.println("Incorrect input!!");
            }
        }
    }

    private void signIn() throws IOException {
        String user = inputProcess("username:");
        String pass = inputProcess("password :");
        User result =  users.check(user, pass);
        if(user.equals("admin")&& pass.equals("admin"))
            new AdminMenu();
        else if(result == null)
            System.out.println("Incorrect input !!");
        else
            new UserMenu(result);
    }

    private void signUp() throws IOException {
        String user = inputProcess("username:");
        String pass = inputProcess("password :");
        if(!(user.equals("admin"))) {
            if (!users.add(new User(user, pass).toString()))
                System.out.println("An account with submitted username and password currently existed");
        }else
            System.out.println("An account with submitted username and password currently existed");

    }




}
