package no.ntnu.imt3673.group2.colourvisiondeficiencytest.core;


import java.util.ArrayList;
import java.util.List;

public class Test <T extends Plate>{
    TestInfo info;
    List<T> plates = new ArrayList<>();

    public Test() {
    }

    public Test(TestInfo info, List<T> plates) {
        this.info = info;
        this.plates = plates;
    }

    public TestInfo getInfo() {
        return info;
    }

    public void setInfo(TestInfo info) {
        this.info = info;
    }

    public List<T> getPlates() {
        return plates;
    }

    public void setPlates(List<T> plates) {
        this.plates = plates;
    }
}
