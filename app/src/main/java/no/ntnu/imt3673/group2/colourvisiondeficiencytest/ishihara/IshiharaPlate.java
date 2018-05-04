package no.ntnu.imt3673.group2.colourvisiondeficiencytest.ishihara;

import no.ntnu.imt3673.group2.colourvisiondeficiencytest.core.Plate;

/**
 * A class that represents a plate object for Ishihara test
 */
public class IshiharaPlate extends Plate {
    private Integer normal;
    private Integer protanStrong;
    private Integer deutanStrong;
    private Integer total;
    private boolean extra;

    /**
     * Empty constructor
     */
    public IshiharaPlate() {
    }

    /**
     *
     * @param id plate id
     * @param filename picture filename
     * @param normal an answer indicating normal vision
     * @param protanStrong an answer indicating red-green deficiency (protan)
     * @param deutanStrong an answer indicating red-green deficiency (deutan)
     * @param total an answer indicating total colour deficiency
     * @param extra additional parameter if the second question could be asked (not used)
     */
    public IshiharaPlate(int id, String filename, int normal, int protanStrong, int deutanStrong, int total, boolean extra) {
        super(id, filename);
        this.normal = normal;
        this.protanStrong = protanStrong;
        this.deutanStrong = deutanStrong;
        this.total = total;
        this.extra = extra;
    }

    /**
     * Gets value of normal vision answer.
     * @return int normal vision answer.
     */
    public Integer getNormal() {
        return normal;
    }

    /**
     * Sets value of normal vision answer.
     * @param normal int normal vision answer.
     */
    public void setNormal(Integer normal) {
        this.normal = normal;
    }

    /**
     * Gets red-green deficiency (protan) answer.
     * @return int red-green deficiency (protan) answer.
     */
    public Integer getProtanStrong() {
        return protanStrong;
    }

    /**
     * Sets red-green deficiency (protan) answer
     * @param protanStrong red-green deficiency (protan) answer
     */
    public void setProtanStrong(Integer protanStrong) {
        this.protanStrong = protanStrong;
    }

    /**
     * Gets red-green deficiency (deutan) answer.
     * @return int red-green deficiency (deutan) answer.
     */
    public Integer getDeutanStrong() {
        return deutanStrong;
    }

    /**
     * Sets red-green deficiency (deutan) answer.
     * @param deutanStrong red-green deficiency (deutan) answer.
     */
    public void setDeutanStrong(Integer deutanStrong) {
        this.deutanStrong = deutanStrong;
    }

    /**
     * Gets total colour deficiency answer.
     * @return total colour deficiency answer.
     */
    public Integer getTotal() {
        return total;
    }

    /**
     * Sets total colour deficiency answer.
     * @param total total colour deficiency answer.
     */
    public void setTotal(Integer total) {
        this.total = total;
    }

    /**
     * Gets additional parameter if the second question could be asked (not used).
     * @return true if extra question needed.
     */
    public boolean isExtra() {
        return extra;
    }

    /**
     * Sets additional parameter if the second question could be asked (not used)
     * @param extra true if extra question needed.
     */
    public void setExtra(boolean extra) {
        this.extra = extra;
    }
}
