package no.ntnu.imt3673.group2.colourvisiondeficiencytest.core;

/**
 * This class represents a single plate of a test
 */
public abstract class Plate {
    private int id;
    private String filename;

    /**
     * Empty constructor
     */
    public Plate() {
    }

    /**
     * Constructor
     * @param id id of the plate
     * @param filename file name of the plate.
     */
    public Plate(int id, String filename) {
        this.id = id;
        this.filename = filename;
    }

    /**
     * Gets the id of a plate
     * @return id of the plate
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the id of a plate
     * @param id id of the plate
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Gets the file name of a plate
     * @return file name of a plate
     */
    public String getFilename() {
        return filename;
    }

    /**
     * Sets the file name of a plate
     * @param filename The file name of a plate
     */
    public void setFilename(String filename) {
        this.filename = filename;
    }
}
