package no.ntnu.imt3673.group2.colourvisiondeficiencytest.core;

/**
 * This class represent the result of a certain plate.
 * @param <T> Generic Type that extens Plate class.
 */
public abstract class Result <T extends Plate> {
    private T plate;

    /**
     * Empty constructor.
     */
    public Result() {
    }

    /**
     * Constructor.
     * @param plate Plate this result is for.
     */
    public Result(T plate) {
        this.plate = plate;
    }

    /**
     * Gets the plate of the result.
     * @return the plate of the result.
     */
    public T getPlate() {
        return plate;
    }

    /**
     * Sets the plate of the result.
     * @param plate the plate of the result.
     */
    public void setPlate(T plate) {
        this.plate = plate;
    }
}
