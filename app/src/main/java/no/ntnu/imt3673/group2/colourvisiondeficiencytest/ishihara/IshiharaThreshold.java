package no.ntnu.imt3673.group2.colourvisiondeficiencytest.ishihara;

/**
 * Class that holds the thresholds for a Ishihara tast
 */
public class IshiharaThreshold {
    private Integer normal;
    private Integer deficiency;
    private Integer timeLimit;

    /**
     * Empty constructor.
     */
    public IshiharaThreshold() {
    }

    /**
     * Gets the threshold for normal colour vision.
     * @return   the threshold for normal colour vision.
     */
    public Integer getNormal() {
        return normal;
    }

    /**
     * Sets the threshold for normal colour vision.
     * @param normal the threshold for normal colour vision.
     */
    public void setNormal(Integer normal) {
        this.normal = normal;
    }

    /**
     * Get the threshold for deficiency colour vision.
     * @return the threshold for deficiency colour vision.
     */
    public Integer getDeficiency() {
        return deficiency;
    }

    /**
     * Sets the threshold for deficiency colour vision.
     * @param deficiency the threshold for deficiency colour vision.
     */
    public void setDeficiency(Integer deficiency) {
        this.deficiency = deficiency;
    }

    /**
     * Gets the period of time an user can use to answer to a simple plate.
     * @return the period of time an user can use to answer to a simple plate.
     */
    public Integer getTimeLimit() {
        return timeLimit;
    }

    /**
     * Sets the period of time an user can use to answer to a simple plate.
     * @param timeLimit Gets the period of time an user can use to answer to a simple plate.
     */
    public void setTimeLimit(Integer timeLimit) {
        this.timeLimit = timeLimit;
    }
}
