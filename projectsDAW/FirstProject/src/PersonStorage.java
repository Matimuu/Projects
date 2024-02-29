import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * @author Mendoza Perez Omar Enrique
 * @date 2024/01/25 11:52
 *
 * Singleton class
 */
public class PersonStorage implements Serializable {
    //Variables
    private static PersonStorage instance;
    private List<Person> collectionPeople;
    public transient BufferedReader console = new BufferedReader(new InputStreamReader(System.in));
    private File storageFile = new File("resources/personList.bin");


    //Constructors and Initialization
    private PersonStorage() {
        //Reading Objects from file
        try {
            collectionPeople = readFile();
        } catch (RuntimeException e) {
            writeFile(collectionPeople);
        }
    }
    public static PersonStorage getInstance() {
        if (instance == null) return new PersonStorage();
        else return instance;
    }

    //Functionality
    public String addExamplePeople() {
        Person firstPerson = new Person("Omar", 87, 186, 23);
        Person secondPerson = new Person("Sasha", 50, 165, 21);
        Person thirdPerson = new Person("Fernando", 90, 175, 22);
        Person fourthPerson = new Person("Roxana", 50, 163, 34);
        Person fifthPerson = new Person("Luis", 80, 179, 27);

        Collections.addAll(collectionPeople, firstPerson, secondPerson, thirdPerson, fourthPerson, fifthPerson);

        writeFile(collectionPeople);
        return "Example pack was added";
    }

    public String addPerson(Person person) {
            collectionPeople.add(person);

            writeFile(collectionPeople);
            return person.getName() + " was added to list";
    }

    public String clearList() {
        writeFile(null);

        return "\nList is cleared!";
    }

    public String showNamesOfPersons() {
        StringBuilder stringBuilder = new StringBuilder();
        collectionPeople = readFile();
        collectionPeople.forEach(p -> stringBuilder.append(p.getName() + "\n"));

        return stringBuilder.toString();
    }

    public String showListWithBMI() {
        StringBuilder stringBuilder = new StringBuilder();

        collectionPeople = readFile();
        collectionPeople.forEach(p -> stringBuilder.append(p + "\n"));

        return stringBuilder.toString().isEmpty() ? "List is empty" : stringBuilder.toString();
    }

    public String findMaxValue() throws NoSuchElementException {
        collectionPeople = readFile();
        String maxValueBMI = collectionPeople.stream().max((p1,p2) -> (int) (p1.getBmi() - p1.getBmi())).get().toString();

        return maxValueBMI.isEmpty() ? "List is empty" : maxValueBMI;
    }

    private List<Person> readFile() {
        try (ObjectInputStream objectIS = new ObjectInputStream(new FileInputStream(storageFile))
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
        try (ObjectOutputStream objectOS = new ObjectOutputStream(new FileOutputStream(storageFile))
        ) {
            objectOS.writeObject(collectionPeople);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
