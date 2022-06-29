package org;

import java.io.*;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Scanner;

public class CmdProcessor {
    private Scanner scanner = new Scanner(System.in);
    private ArrayList<Record> records = new ArrayList<>();
    private ArrayList<Record> searchResult = new ArrayList<>();

    public void run() throws IOException, ClassNotFoundException {
        cmdLoad();
        while (true) {
            System.out.print("Choose operation:\n" +
                    "add - add new record\n" +
                    "search - search records\n" +
                    "print - show all\n" +
                    "exit - exit application\n" +
                    "save - save record(s)\n" +
                    "load - load records\n" +
                    "edit - edit record\n" +
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
            } else if (cmdName.equals("load")) {
                cmdLoad();
            } else if (cmdName.equals("edit")) {
                cmdEdit();
            }
        }
        cmdSave();

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

        Record record = new Record(firstName, lastName,  phoneNumber, email);
        System.out.print("New record in a phone book: ");
        record.recordView();
        records.add(record);

        System.out.print("Press enter to continue.");
        scanner.nextLine();
    }

    private void cmdSearch() {

        while (true) {


            System.out.println("Enter data for search:  ");
            String search = scanner.nextLine();

            for (Record record : records) {
                if (record.getFirstName().contains(search) || record.getLastName().contains(search) ||
                        record.getEmail().contains(search) || record.getPhoneNumber().contains(search)) {
                    searchResult.add(record);
                }
            }
            if (searchResult.size() == 0) {
                System.out.println("No Data");
            } else {
                for (Record record : searchResult) {
                    record.recordView();
                }
            }

            System.out.println("Press enter to continue search or 'return' to return to the main menu  :");
            String cmd = scanner.nextLine();
            if (cmd.equals("return"))
                break;
//            if (cmd.equals("edit")) {
//
//            }
        }
    }

    private void cmdPrint() {

        for (Record record : records) {
            System.out.print("ID: " + records.indexOf(record) + ", ");
            record.recordView();
        }
        System.out.println("Press enter to continue.");
        scanner.nextLine();

    }

    private void cmdSave() {

        try {
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(("notebook.txt")));

            for (Record record : records) {
                objectOutputStream.writeObject(record);
            }
            objectOutputStream.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void cmdLoad() {
        try {
            ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream("notebook.txt"));
            records.clear();
            while (true) {
                Object read = objectInputStream.readObject();
                if (read == null)
                    break;
                Record record = (Record) read;
                records.add(record);
            }
        } catch (IOException e) {
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void cmdEdit() {
        while (true) {
            for (Record record: records) {
                System.out.print("ID: " + records.indexOf(record) + ", ");
                record.recordView();
            }

            System.out.println("Enter ID of the record you would like to edit or delete: ");
            int ID = scanner.nextInt();
            scanner.nextLine();
            System.out.println(records.get(ID));

            System.out.println("Enter 'edit' or 'delete' record:");
            String cmd = scanner.nextLine();

            if (cmd.equals("delete")) {
                records.remove(ID);
            } else if (cmd.equals("edit")) {
                System.out.println("Enter the Name or press enter: ");
                String newName = scanner.nextLine();

                if (newName.length() > 0) {
                    records.get(ID).setFirstName(newName);
                }

                System.out.println("Enter the Last Name or press enter: ");
                String newLastName = scanner.nextLine();

                if (newLastName.length() > 0) {
                    records.get(ID).setLastName(newLastName);
                }

                System.out.println("Enter the Phone Number or press enter: ");
                String newPhoneNumber = scanner.nextLine();

                if (newPhoneNumber.length() > 0) {
                    records.get(ID).setPhoneNumber(newPhoneNumber);
                }

                System.out.println("Enter Email or press enter: ");
                String newEmail = scanner.nextLine();

                if (newEmail.length() > 0) {
                    records.get(ID).setEmail(newEmail);
                }

                System.out.println("The edited record in a phone book: ");
                records.get(ID).recordView();
            }

            System.out.println("Press enter to continue or 'return' to return to main menu: ");
            cmd = scanner.nextLine();
            if (cmd.equals("return")) {
                break;
            }


        }


    }


}


