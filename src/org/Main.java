package org;

import java.io.IOException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException, ClassNotFoundException {


//        Record record = new Record("Lora", "Smith","546843543543" , "lsmith@gmail.com");
//        record.recordView();

        CmdProcessor processor = new CmdProcessor();
        processor.run();


    }
}
