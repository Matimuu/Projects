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
        try (ObjectInputStream objectIS = new ObjectInputStream(new FileInputStream("/Users/omarenrique/Desktop/tests/testPersons.objct")))
        {
            Object plug = objectIS.readObject();

            if (plug instanceof ArrayList<?>) {
                collectionPeople = (ArrayList<Person>) plug;
            } else {
                collectionPeople = new ArrayList<>();
                objectIS.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    //Functionality
    public void addExamplePeople() {
        Person firstPerson = new Person("Omar", 87, 186, 23);
        Person secondPerson = new Person("Sasha", 50, 165, 21);
        Person thirdPerson = new Person("Fernando", 90, 175, 22);
        Person fourthPerson = new Person("Roxana", 50, 163, 34);
        Person fifthPerson = new Person("Luis", 80, 179, 27);

        Collections.addAll(collectionPeople, firstPerson, secondPerson, thirdPerson, fourthPerson, fifthPerson);

        try (ObjectOutputStream objectOS = new ObjectOutputStream(new FileOutputStream("/Users/omarenrique/Desktop/tests/testPersons.objct")))
        {
            objectOS.writeObject(collectionPeople);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void addPerson() {
        try (ObjectOutputStream objectOS = new ObjectOutputStream(new FileOutputStream("/Users/omarenrique/Desktop/tests/testPersons.objct")))
        {
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

            objectOS.writeObject(collectionPeople);

        } catch (NumberFormatException e) {
            System.out.println("Its incorrect value");
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void clearList() {
        collectionPeople = new ArrayList<>();
        System.out.println("\nList is cleared!");
    }

    public void showNamesOfPerson() {
        collectionPeople.forEach(p -> System.out.println(p.getName()));
    }

    public void showListBMI() {
        collectionPeople.forEach(System.out::println);
    }
}
