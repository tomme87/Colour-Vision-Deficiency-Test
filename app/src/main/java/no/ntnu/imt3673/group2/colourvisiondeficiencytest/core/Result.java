package no.ntnu.imt3673.group2.colourvisiondeficiencytest.core;

public abstract class Result <T extends Plate> {
    private T plate;

    public Result() {
    }

    public Result(T plate) {
        this.plate = plate;
    }

    public T getPlate() {
        return plate;
    }

    public void setPlate(T plate) {
        this.plate = plate;
    }
}
