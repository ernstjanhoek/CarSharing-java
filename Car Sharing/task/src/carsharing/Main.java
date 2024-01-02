package carsharing;

import java.sql.*;
import java.util.Scanner;
public class Main {
    static String connectionUrl;
    public static void main(String[] args) throws SQLException, ClassNotFoundException, InterruptedException {
        //  database URL
        connectionUrl = "jdbc:h2:./src/carsharing/db/" + args[1];
        Scanner scanner = new Scanner(System.in);
        CarSharingApp menu = new Init();
        try {
            menu.processInput();
            menu = menu.transition();
        } catch (Exception ignore) {}

        while (menu.running) {
            try {
                System.out.println(menu.displayMessage());
                menu.setInput(scanner.nextLine());
                menu.processInput();
                menu = menu.transition();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }
}