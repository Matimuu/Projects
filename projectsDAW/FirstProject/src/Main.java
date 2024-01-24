import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Author: Omar Enrique Mendoza Perez
 * Date: 17/1/2024.
 */
public class Main {
    //Variables
    private List<Person> collectionPeople = new ArrayList<>();
    private int option = -1;
    private BufferedReader console = new BufferedReader(new InputStreamReader(System.in));
    private static Main main = new Main();

    public static void main(String[] args) throws IOException {

        do {
            System.out.println("===============");
            System.out.println("Menu of Options");
            System.out.println("===============");
            System.out.println("Choose one option:");
            System.out.println("1. Add person");
            System.out.println("2. Show Persons");
            System.out.println("3. Show BMI of people");
            System.out.println("4. Add example people");
            System.out.println("5. Exit");

            main.option = Integer.parseInt(main.console.readLine());

            switch (main.option) {
                case 1 -> main.addPerson();
                case 2 -> main.collectionPeople.forEach(p -> System.out.println(p.getName()));
                case 3 -> main.collectionPeople.forEach(System.out::println);
                case 4 -> main.addExamplePeople();
                case 5 -> {
                    main.console.close();
                    return;
                }
                default -> System.out.println("Wrong value, try again");
            }
        } while (true);

    }


    public void addExamplePeople() {
        Person firstPerson = new Person("Omar", 87, 186, 23);
        Person secondPerson = new Person("Sasha", 50, 165, 21);
        Person thirdPerson = new Person("Fernando", 90, 175, 22);
        Person fourthPerson = new Person("Roxana", 50, 163, 34);
        Person fifthPerson = new Person("Luis", 80, 179, 27);

        Collections.addAll(collectionPeople, firstPerson, secondPerson, thirdPerson, fourthPerson, fifthPerson);
    }

    //Manual entering data to program
    //Form
    public void addPerson() throws IOException {
        try {
            Person plugPerson = new Person();

            System.out.println("Enter your name: ");
            plugPerson.setName(console.readLine());

            System.out.print("Enter your age: ");
            plugPerson.setAge(Integer.parseInt(console.readLine()));

            System.out.print("Enter your height: ");
            plugPerson.setHeight(Double.parseDouble(console.readLine()));

            System.out.print("Enter your weight: ");
            plugPerson.setWeight(Double.parseDouble(console.readLine()));

            collectionPeople.add(plugPerson);
            System.out.println(plugPerson.getName() + " was added to list");

        } catch (NumberFormatException e) {
            System.out.println("Its incorrect value");
            e.printStackTrace();
        }
    }
}
