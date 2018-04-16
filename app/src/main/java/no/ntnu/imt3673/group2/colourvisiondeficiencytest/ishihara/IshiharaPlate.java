package no.ntnu.imt3673.group2.colourvisiondeficiencytest.ishihara;

import no.ntnu.imt3673.group2.colourvisiondeficiencytest.core.Plate;

public class IshiharaPlate extends Plate {
    private Integer normal;
    private Integer protanStrong;
    private Integer deutanStrong;
    private Integer total;
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

    public Integer getNormal() {
        return normal;
    }

    public void setNormal(Integer normal) {
        this.normal = normal;
    }

    public Integer getProtanStrong() {
        return protanStrong;
    }

    public void setProtanStrong(Integer protanStrong) {
        this.protanStrong = protanStrong;
    }

    public Integer getDeutanStrong() {
        return deutanStrong;
    }

    public void setDeutanStrong(Integer deutanStrong) {
        this.deutanStrong = deutanStrong;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public boolean isExtra() {
        return extra;
    }

    public void setExtra(boolean extra) {
        this.extra = extra;
    }
}
