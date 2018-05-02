package no.ntnu.imt3673.group2.colourvisiondeficiencytest.ishihara;

/**
 * Class that holds the thresholds for a Ishihara tast
 */

public class IshiharaThreshold {
    private Integer normal;
    private Integer deficiency;

    public IshiharaThreshold() {
    }

    public Integer getNormal() {
        return normal;
    }

    public void setNormal(Integer normal) {
        this.normal = normal;
    }

    public Integer getDeficiency() {
        return deficiency;
    }

    public void setDeficiency(Integer deficiency) {
        this.deficiency = deficiency;
    }
}
