package no.ntnu.imt3673.group2.colourvisiondeficiencytest.core;

public abstract class Plate {
    private int id;
    private String filename;

    public Plate() {
    }

    public Plate(int id, String filename) {
        this.id = id;
        this.filename = filename;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }
}
