package sample.data;

public final class Shift {
    private int number;
    private String description;

    public Shift(int number, String description) {
        this.number = number;
        this.description = description;
    }

    public int getNumber() {
        return number;
    }

    public String getDescription() {
        return description;
    }
}
