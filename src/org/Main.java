package org;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException, ClassNotFoundException {
//	Record record = new Record("Lora", "Smith", "lsmith@gmail.com", "675 9874318");
//        record.toString();
        CmdProcessor processor = new CmdProcessor();
        processor.run();
    }
}
