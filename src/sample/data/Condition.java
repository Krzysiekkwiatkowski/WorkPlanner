package sample.data;

public class Condition {
    private int shiftNumber;
    private boolean possibleShift;

    public Condition(int shiftNumber, boolean possibleShift) {
        this.shiftNumber = shiftNumber;
        this.possibleShift = possibleShift;
    }

    public int getShiftNumber() {
        return shiftNumber;
    }

    public boolean isPossibleShift() {
        return possibleShift;
    }
}
