package org;


import java.io.*;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Scanner;

public class CmdProcessor {
    private Scanner scanner = new Scanner(System.in);
    private ArrayList<Record> records = new ArrayList<>();

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
            } else if(cmdName.equals("load")) {
                cmdLoad();
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

        Record record = new Record(firstName, lastName, email, phoneNumber);
        System.out.print("New record in a phone book: ");
        record.recordView();
        records.add(record);

        System.out.print("Press enter to continue.");
        scanner.nextLine();
    }

    private void cmdSearch() throws IOException, ClassNotFoundException {

        System.out.println("This feature is under construction. Press Enter to continue");
    }

    private void cmdPrint() {

        for (Record record : records) {
            record.recordView();
        }
        System.out.print("Press enter to continue.");
        scanner.nextLine();

    }

    private void cmdSave() {

        try {
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(("notebook.txt"), true));

            for (Record record : records) {
                objectOutputStream.writeObject(record);
            }
            objectOutputStream.close();

        } catch (IOException e) {
            e.printStackTrace();
        }


    }
    private void cmdLoad(){
        try {
            ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream("notebook.txt"));
            records.clear();
            while(true) {
                Object read = objectInputStream.readObject();
                if(read == null)
                    break;
                Record record = (Record) read;
                records.add(record);
            }
        } catch (IOException e) {
            //e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

}
