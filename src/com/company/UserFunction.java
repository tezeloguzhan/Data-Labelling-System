package com.company;

import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

public class UserFunction {
    private User [] userList;
    private User currUser;
    public UserFunction(User [] userList){
        this.userList = userList;
    }

    public User getCurrUser() {
        return currUser;
    }

    public boolean [] authenticateUser(String userName, String userPass){
        boolean authName = false;
        boolean authPass = false;
        for (User user: this.userList){
            if(userName.equals(user.getUserName())){
                authName = true;
                if(userPass.equals(user.getPassword())){
                    authPass = true;
                    this.currUser = user;
                    break;
                }
            }
        }
        return new boolean[]{authName, authPass};
    }

    public User userLogin() {
        String name = "";
        String pass = "";
        Scanner scan = new Scanner(System.in);
        while (true) {
            System.out.println("----------- User Login System -------------");
            System.out.print("Enter User Name:");
            name = scan.nextLine();
            System.out.print("Enter Password:");
            pass = scan.nextLine();
            if (name.isEmpty() == true && pass.isEmpty() ==true){
                return new User(ThreadLocalRandom.current().nextInt(1000, 10000 + 1), "bot", "1", "defaultBot", 0.1);
            }
            boolean[] authList = this.authenticateUser(name, pass);
            boolean rowResult = true;
            for (Boolean el : authList) {
                rowResult = el && rowResult;
            }
            if (rowResult == true) {
                System.out.println("User Logged In");
                System.out.print("Current User: ");
                System.out.print(this.currUser.getJsonObject());
                return this.currUser;

            } else {
                if (authList[0] == false) {
                    System.out.println("Entered User Name is Incorrect..");
                    System.out.println("Entered User Password is Incorrect..");
                } else if (authList[0] == true && authList[1] == false) {
                    System.out.println("Entered User Password is Incorrect..");
                }
                System.out.println("Press Enter to Try Again");
                scan.nextLine();
            }

        }
    }
}
