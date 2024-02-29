package org.example;

/**
 * @author Mendoza Perez Omar Enrique
 * @date 2024/02/29 10:55
 *
 */
import java.io.*;
import java.io.File;

public class MainCreator {
    public static void main(String[] args) throws IOException {
        File file = new File("src/main/java/org/example/main.java");
        String app = """
                package org.example;
                        
                /**
                 * @author Mendoza Perez Omar Enrique
                 * @date 2024/02/29 12:54
                 */
                public class main {
                    public static void main(String[] args) {
                        System.out.println("Hola mundo !");
                    }
                }
        """;

        try(BufferedWriter bwf = new BufferedWriter(new FileWriter(file))) {
            bwf.write(app);
        }
    }
}
