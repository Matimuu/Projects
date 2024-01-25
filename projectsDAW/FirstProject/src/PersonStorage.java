import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author Mendoza Perez Omar Enrique
 * @date 2024/01/25 11:52
 */
public class PersonStorage implements Serializable {
    //Variables
    private List<Person> collectionPeople;
    public transient BufferedReader console = new BufferedReader(new InputStreamReader(System.in));

    //Constructors
    public PersonStorage() {
        //Reading Objects from file
        collectionPeople = readFile();
    }

    //Functionality
    public void addExamplePeople() {
        Person firstPerson = new Person("Omar", 87, 186, 23);
        Person secondPerson = new Person("Sasha", 50, 165, 21);
        Person thirdPerson = new Person("Fernando", 90, 175, 22);
        Person fourthPerson = new Person("Roxana", 50, 163, 34);
        Person fifthPerson = new Person("Luis", 80, 179, 27);

        Collections.addAll(collectionPeople, firstPerson, secondPerson, thirdPerson, fourthPerson, fifthPerson);

        writeFile(collectionPeople);
    }

    public void addPerson() {
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

            writeFile(collectionPeople);

        } catch (NumberFormatException e) {
            System.out.println("Its incorrect value");
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void clearList() {
        writeFile(null);
        System.out.println("\nList is cleared!");
    }

    public void showNamesOfPerson() {
        collectionPeople = readFile();
        collectionPeople.forEach(p -> System.out.println(p.getName()));
    }

    public void showListBMI() {
        collectionPeople = readFile();
        collectionPeople.forEach(System.out::println);
    }

    private List<Person> readFile() {
        try (ObjectInputStream objectIS = new ObjectInputStream(new FileInputStream("/Users/omarenrique/Desktop/tests/testPersons.objct"))
        ) {
            Object plug = objectIS.readObject();

            if (plug instanceof ArrayList<?>) {
                collectionPeople = (ArrayList<Person>) plug;
            } else {
                collectionPeople = new ArrayList<>();
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return collectionPeople;
    }

    private void writeFile(List<Person> collectionPeople) {
        try (ObjectOutputStream objectOS = new ObjectOutputStream(new FileOutputStream("/Users/omarenrique/Desktop/tests/testPersons.objct"))
        ) {
            objectOS.writeObject(collectionPeople);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
