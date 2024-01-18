/**
 * Author: Omar Enrique Mendoza Perez
 * Date: 17/1/2024
 */

public class Person {
    //Variables
    private double weight;
    private double height;
    private int age;
    private double bmi;
    private String name;

    //Constructor
    public Person(String name, double weight, double height, int age) throws DataException {
        if (weight > 0 && height > 0 && age > 0 && !name.equals("") && name != null) {
            this.weight = weight;
            this.height = height;
            this.age = age;
            this.name = name;
        }
        else throw new DataException();

        calculateBMI();
    }

    //Functionality
    private void calculateBMI() {
        bmi = (weight / height / height) * 10_000;
    }

    @Override
    public String toString() {
        return String.format("%s, with your age(%d), height(%.2f) and weight(%.2f), you have BMI rating %.2f", name, age, height, weight, bmi);
    }

    //Setters and Getters
    public void setWeight(double weight) {
        if (weight > 0) this.weight = weight;
        else throw new DataException();
        calculateBMI();
    }

    public void setHeight(double height) {
        if (height > 0) this.height = height;
        else throw new DataException();
        calculateBMI();
    }

    public void setAge(int age) {
        if (age > 0) this.age = age;
        else throw new DataException();
    }

    public double getWeight() {
        return weight;
    }

    public double getHeight() {
        return height;
    }

    public int getAge() {
        return age;
    }
}
