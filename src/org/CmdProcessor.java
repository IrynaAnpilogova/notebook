package org;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Scanner;

public class CmdProcessor {
    private Scanner scanner = new Scanner(System.in);
    private ArrayList<Record> records = new ArrayList<Record>();

    public void run() {
        while (true) {
            System.out.print("Choose operation:\n" +
                    "add - add new record\n" +
                    "search - search records\n" +
                    "print - show all\n" +
                    "exit - exit application\n" +
                    "save - saverecords\n" +
                    "> ");
            String cmdName = scanner.nextLine();

            if (cmdName.equals("add")) {
                cmdAdd();
            } else if (cmdName.equals("search")) {
                cmdSearch();
            } else if (cmdName.equals("exit")) {
                break;
            } else if (cmdName.equals("print")) {
                cmdPrint();
            } else if (cmdName.equals("save")) {
                cmdSave();
            }
        }
    }

    private void cmdAdd() {
        System.out.print("Enter First Name: ");
        String firstName = scanner.nextLine();
        System.out.print("Enter Last Name: ");
        String lastName = scanner.nextLine();
        System.out.print("Enter Phone Number: ");
        String phoneNumber = scanner.nextLine();
        System.out.print("Enter Email: ");
        String email = scanner.nextLine();

        Record record = new Record(firstName, lastName, email, phoneNumber);
        System.out.print("New record in a phone book: ");
        record.recordView();
        records.add(record);

        System.out.print("Press enter to continue.");
        scanner.nextLine();
    }

    private void cmdSearch() {
        System.out.println("This feature is under construction. Press Enter to continue.");
    }

    private void cmdPrint() {
        for (Record record : records) {
            record.recordView();
        }
        System.out.print("Press enter to continue.");
        scanner.nextLine();
    }
    private void cmdSave() {
        System.out.println("This feature is under construction. Press Enter to continue.");
    }

}
