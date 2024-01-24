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
    private String name, bmiPrime;

    //Constructors
    public Person(String name, double weight, double height, int age) throws DataException {
        if (weight > 0 && height > 0 && age > 0 && !name.equals("") && name != null) {
            this.weight = weight;
            this.height = height;
            this.age = age;
            this.name = name;
        } else throw new DataException();

        calculateBMI();
    }

    public Person() {

    }

    //Functionality
    private void calculateBMI() {
        bmi = (weight / height / height) * 10_000;
        checkBMIprime();
    }

    private void checkBMIprime() {
        if (bmi < 16) bmiPrime = "Severe Thinness";
        else if (bmi >= 16 && bmi <= 17) bmiPrime = "Moderate Thinness";
        else if (bmi >= 17 && bmi <= 18.5) bmiPrime = "Mild Thinness";
        else if (bmi >= 18.5 && bmi <= 25) bmiPrime = "Normal";
        else if (bmi >= 25 && bmi <= 30) bmiPrime = "Overweight";
        else if (bmi >= 30 && bmi <= 35) bmiPrime = "Obese Class I";
        else if (bmi >= 35 && bmi <= 40) bmiPrime = "Obese Class II";
        else bmiPrime = "Obese Class III";
    }

    //Setters and Getters
    public void setName(String name) {
        this.name = name;
    }
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

    public String getName() {
        return name;
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

    @Override
    public String toString() {
        return String.format("%-9s with your age(%d), height(%.2f) and weight(%.2f), you have BMI rating %.2f and BMI prime: %s", name + ",", age, height, weight, bmi, bmiPrime);
    }
}
