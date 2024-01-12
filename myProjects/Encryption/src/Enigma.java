import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Enigma {
    public static void main(String[] args) throws IOException {
        List<Integer> toEnigma = new ArrayList<>();

        try (BufferedReader buffConsole = new BufferedReader(new InputStreamReader(System.in));
             FileInputStream fileIS = new FileInputStream(buffConsole.readLine());
             FileOutputStream fileOs = new FileOutputStream(buffConsole.readLine())
        ) {
            while (fileIS.available() > 0) {
                toEnigma.add(fileIS.read());
            }

            int size = toEnigma.size();

            toEnigma = toEnigma.stream().map(i -> i ^ size).collect(Collectors.toList());
            toEnigma.forEach(i -> {
                try {
                    fileOs.write(i);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        }
    }
}
