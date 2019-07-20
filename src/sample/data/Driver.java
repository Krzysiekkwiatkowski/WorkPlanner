package sample.data;

public class Driver {
    private int number;
    private String firstName;
    private String lastName;
    private int phone;

    public Driver(int number, String firstName, String lastName, int phone) {
        this.number = number;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
    }

    public int getNumber() {
        return number;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public int getPhone() {
        return phone;
    }

    @Override
    public String toString() {
        return this.number + " " + this.firstName + " " + this.lastName + " " + this.phone;
    }
}
