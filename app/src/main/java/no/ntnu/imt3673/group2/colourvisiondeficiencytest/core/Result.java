package no.ntnu.imt3673.group2.colourvisiondeficiencytest.core;

public abstract class Result {
    private int plateId;

    public Result() {
    }

    public Result(int plateId) {
        this.plateId = plateId;
    }

    public int getPlateId() {
        return plateId;
    }

    public void setPlateId(int plateId) {
        this.plateId = plateId;
    }
}
