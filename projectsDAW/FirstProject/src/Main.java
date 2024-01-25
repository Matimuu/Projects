import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Author: Omar Enrique Mendoza Perez
 * Date: 17/1/2024.
 */
public class Main {
    private static BufferedReader console;
    private static PersonStorage ps;
    public static void main(String[] args) throws Exception {
        ps = PersonStorage.getInstance();
        Menu(ps);
    }

    private static void Menu(PersonStorage ps) throws IOException {
        do {
            System.out.println("===============");
            System.out.println("Menu of Options");
            System.out.println("===============");
            System.out.println("Choose one option:");
            System.out.println("1. Add person");
            System.out.println("2. Show Persons");
            System.out.println("3. Show BMI of people");
            System.out.println("4. Add example people");
            System.out.println("5. Clear list");
            System.out.println("6. Exit");

            int option = Integer.parseInt(ps.console.readLine());

            switch (option) {
                case 1 -> System.out.println(addPerson());
                case 2 -> System.out.println(ps.showNamesOfPersons());
                case 3 -> System.out.println(ps.showListWithBMI());
                case 4 -> System.out.println(ps.addExamplePeople());
                case 5 -> System.out.println(ps.clearList());
                case 6 -> {
                    return;
                }
                default -> System.out.println("Wrong value, try again");
            }
        } while (true);
    }

    private static String addPerson() {
        console = new BufferedReader(new InputStreamReader(System.in));

        Person plugPerson = new Person();
        try {
            System.out.println("Enter your name: ");
            plugPerson.setName(console.readLine());

            System.out.print("Enter your age: ");
            plugPerson.setAge(Integer.parseInt(console.readLine()));

            System.out.print("Enter your height: ");
            plugPerson.setHeight(Double.parseDouble(console.readLine()));

            System.out.print("Enter your weight: ");
            plugPerson.setWeight(Double.parseDouble(console.readLine()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return ps.addPerson(plugPerson);
    }
}
