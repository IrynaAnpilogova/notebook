package org;

import java.io.*;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.InputMismatchException;
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
        String firstName;
        String lastName;
        String phoneNumber;
        String email;

        while (true) {
            System.out.print("Enter First Name: ");
            firstName = scanner.nextLine();

            System.out.print("Enter Last Name: ");
            lastName = scanner.nextLine();

            System.out.print("Enter Phone Number: ");
            phoneNumber = scanner.nextLine();

            System.out.print("Enter Email: ");
            email = scanner.nextLine();

            //validate user input and display error message
            System.out.println("Validate result: ");

            if (!ValidateInput.validateFirstName(firstName)) {
                System.out.println("Invalid First Name");
            } else if (!ValidateInput.validateLastName(lastName)) {
                System.out.println("Invalid Last Name");
            } else if (!ValidateInput.validatePhoneNumber(phoneNumber)) {
                System.out.println("Invalid Phone Number");
            } else if (!ValidateInput.validateEmail(email)) {
                System.out.println("Invalid Email");
            } else {
                System.out.println("Valid input.Thank you!");
                break;
            }
        }

        Record record = new Record(firstName, lastName, phoneNumber, email);
        System.out.print("New record in a phone book: ");
        record.recordView();
        records.add(record);

        System.out.print("Press enter to continue.");
        scanner.nextLine();
    }

    private void cmdSearch() {
        String search;

        while (true) {

            while (true) {
                System.out.print("Enter data for search:  ");
                search = scanner.nextLine();
                if (search.length() != 0) {
                    break;
                }
            }

            searchResult.clear();
            for (Record record : records) {
                if (search.length() == 0) {
                    break;
                }
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

            System.out.print("Press enter to continue search or 'return' to return to the main menu  :");
            String cmd = scanner.nextLine();
            if (cmd.equals("return"))
                break;

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
        int ID;
        while (true) {

            for (Record record : records) {
                System.out.print("ID: " + records.indexOf(record) + ", ");
                record.recordView();
            }

            while (true) {
                System.out.print("Enter ID of the record you would like to edit or delete: ");
                String value = scanner.nextLine();
                if (value.matches("^[0-9]+$")) {
                    System.out.println("valid");
                    ID = Integer.parseInt(value);
                    System.out.println(ID);

                    if (ID > records.size() -1 || ID < 0) {
                        System.out.println("You have entered wrong ID! You ID is out of range.");
                        continue;
                    } else {
                        break;
                    }

                } else {
                    System.out.println("not valid");
                    continue;
                }
            }


            records.get(ID).recordView();

            System.out.print("Enter 'edit' or 'delete' record: ");
            String cmd = scanner.nextLine();

            if (cmd.equals("delete")) {
                System.out.println("The record " + records.get(ID) + " was deleted");
                records.remove(ID);

            } else if (cmd.equals("edit")) {

                System.out.println("The Name you are about to change is: " + "'" + records.get(ID).getFirstName() + "'" + ". Enter the new Name or press enter: ");
                String newName = scanner.nextLine();

                if (newName.length() > 0) {
                    records.get(ID).setFirstName(newName);
                }

                System.out.println("The Last Name you are about to change is: " + "'" + records.get(ID).getLastName() + "'" + ". Enter the Last Name or press enter: ");
                String newLastName = scanner.nextLine();

                if (newLastName.length() > 0) {
                    records.get(ID).setLastName(newLastName);
                }

                System.out.println("The Phone Number you are about to change is: " + "'" + records.get(ID).getPhoneNumber() + "'" + ". Enter the Phone Number or press enter: ");
                String newPhoneNumber = scanner.nextLine();

                if (newPhoneNumber.length() > 0) {
                    records.get(ID).setPhoneNumber(newPhoneNumber);
                }

                System.out.println("The Email you are about to change is: " + "'" + records.get(ID).getEmail() + "'" + ". Enter Email or press enter: ");
                String newEmail = scanner.nextLine();

                if (newEmail.length() > 0) {
                    records.get(ID).setEmail(newEmail);
                }

                System.out.print("The edited record in a phone book: ");
                records.get(ID).recordView();
            }

            System.out.println("Press enter to continue or 'return' to return to main menu: ");
            cmd = scanner.nextLine();
            if (cmd.equals("return") ) {
                break;
            }


        }


    }


}


