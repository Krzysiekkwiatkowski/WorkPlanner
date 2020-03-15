package com.programs.data;

public class Driver implements Comparable<Driver> {
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

    public void setNumber(int number) {
        this.number = number;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return this.number + " " + this.firstName + " " + this.lastName + " " + (this.phone != 0 ? this.phone : "");
    }

    @Override
    public int compareTo(Driver o) {
        if (this.getNumber() < o.getNumber()) {
            return -1;
        } else if (this.getNumber() > o.getNumber()) {
            return 1;
        }
        return 0;
    }
}
