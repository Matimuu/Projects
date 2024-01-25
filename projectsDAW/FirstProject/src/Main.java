import java.io.IOException;

/**
 * Author: Omar Enrique Mendoza Perez
 * Date: 17/1/2024.
 */
public class Main {
    public static void main(String[] args) throws Exception {
        PersonStorage ps = new PersonStorage();
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
                case 1 -> ps.addPerson();
                case 2 -> ps.showNamesOfPerson();
                case 3 -> ps.showListBMI();
                case 4 -> ps.addExamplePeople();
                case 5 -> ps.clearList();
                case 6 -> {
                    return;
                }
                default -> System.out.println("Wrong value, try again");
            }
        } while (true);
    }
}
