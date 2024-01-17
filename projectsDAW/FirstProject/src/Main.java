/*
 * Author: Omar Enrique Mendoza Perez
 * Date: 17/1/2024.
 */
public class Main {
    public static void main(String[] args) {
        Person firstPerson = new Person("Omar", 87, 186,23);
        System.out.println(firstPerson);

        firstPerson.setHeight(70);
        System.out.println(firstPerson);

        Person errorPerson = new Person("Error", -1, 12, 0);
        System.out.println(errorPerson);

    }
}
