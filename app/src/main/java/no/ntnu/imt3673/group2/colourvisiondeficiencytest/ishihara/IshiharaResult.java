package no.ntnu.imt3673.group2.colourvisiondeficiencytest.ishihara;

import no.ntnu.imt3673.group2.colourvisiondeficiencytest.core.Result;

/**
 * Holds a result for a Plate.
 */
public class IshiharaResult extends Result<IshiharaPlate> {
    private Integer answer;

    public IshiharaResult() {
        /* Required empty constructor */
    }

    /**
     * Constructor that can create a result for a plate.
     * @param plate The plate we got a result for.
     * @param answer What the user answered.
     */
    public IshiharaResult(IshiharaPlate plate, Integer answer) {
        super(plate);
        this.answer = answer;
    }

    /**
     * Get the answer.
     * @return what the user answered.
     */
    public Integer getAnswer() {
        return answer;
    }

    /**
     * Set the answer.
     * @param answer what the user answered.
     */
    public void setAnswer(Integer answer) {
        this.answer = answer;
    }

}
