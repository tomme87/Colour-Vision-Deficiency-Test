package no.ntnu.imt3673.group2.colourvisiondeficiencytest.ishihara;

import no.ntnu.imt3673.group2.colourvisiondeficiencytest.core.Plate;

public class IshiharaPlate extends Plate {
    private int normal;
    private int protanStrong;
    private int deutanStrong;
    private int total;
    private boolean extra;

    public IshiharaPlate() {
    }

    public IshiharaPlate(int id, String filename, int normal, int protanStrong, int deutanStrong, int total, boolean extra) {
        super(id, filename);
        this.normal = normal;
        this.protanStrong = protanStrong;
        this.deutanStrong = deutanStrong;
        this.total = total;
        this.extra = extra;
    }

    public int getNormal() {
        return normal;
    }

    public void setNormal(int normal) {
        this.normal = normal;
    }

    public int getProtanStrong() {
        return protanStrong;
    }

    public void setProtanStrong(int protanStrong) {
        this.protanStrong = protanStrong;
    }

    public int getDeutanStrong() {
        return deutanStrong;
    }

    public void setDeutanStrong(int deutanStrong) {
        this.deutanStrong = deutanStrong;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public boolean isExtra() {
        return extra;
    }

    public void setExtra(boolean extra) {
        this.extra = extra;
    }
}
