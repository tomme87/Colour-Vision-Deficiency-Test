package no.ntnu.imt3673.group2.colourvisiondeficiencytest.ishihara;

import java.util.List;

/**
 * Class to calculate Results of the test. finds if the user have normal colour vision or not.
 */

public class IshiharaCalculateResults {
    public static final int NORMAL = 0;
    public static final int GENERAL_READ_GREEN = 1;
    public static final int PROTAN = 2;
    public static final int DEUTAN = 3;
    public static final int UNCERTAIN = 4;

    private static final int COUNTER_NORMAL = 0;
    private static final int COUNTER_DEUTAN = 1;
    private static final int COUNTER_PROTAN = 2;
    private static final int COUNTR_TOTAL = 3;

    //public static final int[] counters = {0,0,0,0};

    private IshiharaCalculateResults() {
        /* Empty constructor to prevent intialiation */
    }

    public static int getResult(IshiharaThreshold ishiharaThreshold, List<IshiharaResult> results) {
        int[] counters = calculate(results);

        int resultsNormal = counters[COUNTER_NORMAL];

        if (resultsNormal > ishiharaThreshold.getNormal()) {
            return NORMAL;
        }
        if (resultsNormal < ishiharaThreshold.getDeficiency()) {
            if (counters[IshiharaCalculateResults.COUNTER_DEUTAN] >
                    counters[IshiharaCalculateResults.COUNTER_PROTAN]) {
                return DEUTAN;
            }
            if (counters[IshiharaCalculateResults.COUNTER_DEUTAN] <
                    counters[IshiharaCalculateResults.COUNTER_PROTAN]) {
                return PROTAN;
            }
            return GENERAL_READ_GREEN;
        }

        return UNCERTAIN;
    }

    private static int[] calculate(final List<IshiharaResult> results) {
        final int[] counters = {0, 0, 0, 0};
        boolean matched;

        for (IshiharaResult result : results) {
            IshiharaPlate plate = result.getPlate();
            Integer answer = result.getAnswer();

            matched = isMatched(plate, answer, counters);
            if (!matched) {
                // treat unmatched answer as "null"
                isMatched(plate, null, counters);
            }
        }

        //runIshiharaTestSummary();
        return counters;
    }

    private static boolean isMatched(final IshiharaPlate plate, final Integer answer, final int[] counters) {
        if (answer == null) {
            return false;
        }

        boolean matched = false;

        if (answer.equals(plate.getNormal())) {
            counters[COUNTER_NORMAL]++;
            matched = true;
        }
        if (answer.equals(plate.getDeutanStrong())) {
            counters[COUNTER_DEUTAN]++;
            matched = true;
        }
        if (answer.equals(plate.getProtanStrong())) {
            counters[COUNTER_PROTAN]++;
            matched = true;
        }
        if (answer.equals(plate.getTotal())) {
            counters[COUNTR_TOTAL]++;
            matched = true;
        }
        return matched;
    }
}
