package no.ntnu.imt3673.group2.colourvisiondeficiencytest.ishihara;

import no.ntnu.imt3673.group2.colourvisiondeficiencytest.core.Plate;
import no.ntnu.imt3673.group2.colourvisiondeficiencytest.core.Result;

public class IshiharaResult extends Result<IshiharaPlate> {
    private Integer answer;

    public IshiharaResult() {
    }

    public IshiharaResult(IshiharaPlate plate, Integer answer) {
        super(plate);
        this.answer = answer;
    }

    public Integer getAnswer() {
        return answer;
    }

    public void setAnswer(Integer answer) {
        this.answer = answer;
    }

}
