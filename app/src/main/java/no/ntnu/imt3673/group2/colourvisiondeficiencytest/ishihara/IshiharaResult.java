package no.ntnu.imt3673.group2.colourvisiondeficiencytest.ishihara;

import no.ntnu.imt3673.group2.colourvisiondeficiencytest.core.Result;

public class IshiharaResult extends Result {
    private int answer;

    public IshiharaResult() {
    }

    public IshiharaResult(int plateId, int answer) {
        super(plateId);
        this.answer = answer;
    }

    public int getAnswer() {
        return answer;
    }

    public void setAnswer(int answer) {
        this.answer = answer;
    }
}
