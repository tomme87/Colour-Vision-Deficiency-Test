package no.ntnu.imt3673.group2.colourvisiondeficiencytest;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.junit.Test;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import no.ntnu.imt3673.group2.colourvisiondeficiencytest.ishihara.IshiharaCalculateResults;
import no.ntnu.imt3673.group2.colourvisiondeficiencytest.ishihara.IshiharaPlate;
import no.ntnu.imt3673.group2.colourvisiondeficiencytest.ishihara.IshiharaResult;
import no.ntnu.imt3673.group2.colourvisiondeficiencytest.ishihara.IshiharaThreshold;

import static org.junit.Assert.*;

/**
 * Test the result from a ishiharatest.
 */

public class IshiharaResultTest {

    private static final String platesJson = "[\n" +
            "  {\n" +
            "  \"id\":1,   \n" +
            "  \"filename\":\"01.gif\",\n" +
            "  \"normal\":12,\n" +
            "  \"protanStrong\": 12,\n" +
            "  \"deutanStrong\": 12,\n" +
            "  \"total\":12, \n" +
            "  \"extra\":false    \n" +
            " },\n" +
            " {\n" +
            "  \"id\":2,   \n" +
            "  \"filename\":\"02.gif\",\n" +
            "  \"normal\":8,\n" +
            "  \"protanStrong\": 3,\n" +
            "  \"deutanStrong\": 3,\n" +
            "  \"total\":null, \n" +
            "  \"extra\":false    \n" +
            " },\n" +
            "  {\n" +
            "  \"id\":3,   \n" +
            "  \"filename\":\"03.gif\",\n" +
            "  \"normal\":29,\n" +
            "  \"protanStrong\": 70,\n" +
            "  \"deutanStrong\": 70,\n" +
            "  \"total\":null, \n" +
            "  \"extra\":false    \n" +
            " },\n" +
            "  {\n" +
            "  \"id\":4,   \n" +
            "  \"filename\":\"05.gif\",\n" +
            "  \"normal\":5,\n" +
            "  \"protanStrong\": 2,\n" +
            "  \"deutanStrong\": 2,\n" +
            "  \"total\":null, \n" +
            "  \"extra\":false    \n" +
            " },\n" +
            " {\n" +
            "  \"id\":5,   \n" +
            "  \"filename\":\"05.gif\",\n" +
            "  \"normal\":3,\n" +
            "  \"protanStrong\": 5,\n" +
            "  \"deutanStrong\": 5,\n" +
            "  \"total\":null, \n" +
            "  \"extra\":false    \n" +
            " },\n" +
            "  {\n" +
            "  \"id\":6,   \n" +
            "  \"filename\":\"06.gif\",\n" +
            "  \"normal\":15,\n" +
            "  \"protanStrong\": 17,\n" +
            "  \"deutanStrong\": 17,\n" +
            "  \"total\":null, \n" +
            "  \"extra\":false    \n" +
            " },\n" +
            "  {\n" +
            "  \"id\":7,   \n" +
            "  \"filename\":\"07.gif\",\n" +
            "  \"normal\":74,\n" +
            "  \"protanStrong\": 21,\n" +
            "  \"deutanStrong\": 21,\n" +
            "  \"total\":null, \n" +
            "  \"extra\":false    \n" +
            " },\n" +
            "  {\n" +
            "  \"id\":8,   \n" +
            "  \"filename\":\"08.gif\",\n" +
            "  \"normal\":6,\n" +
            "  \"protanStrong\": null,\n" +
            "  \"deutanStrong\": null,\n" +
            "  \"total\":null, \n" +
            "  \"extra\":false    \n" +
            " },\n" +
            "  {\n" +
            "  \"id\":9,   \n" +
            "  \"filename\":\"09.gif\",\n" +
            "  \"normal\":45,\n" +
            "  \"protanStrong\": null,\n" +
            "  \"deutanStrong\": null,\n" +
            "  \"total\":null, \n" +
            "  \"extra\":false    \n" +
            " },\n" +
            "  {\n" +
            "  \"id\":10,   \n" +
            "  \"filename\":\"10.gif\",\n" +
            "  \"normal\":5,\n" +
            "  \"protanStrong\": null,\n" +
            "  \"deutanStrong\": null,\n" +
            "  \"total\":null, \n" +
            "  \"extra\":false    \n" +
            " },\n" +
            "  {\n" +
            "  \"id\":11,   \n" +
            "  \"filename\":\"11.gif\",\n" +
            "  \"normal\":7,\n" +
            "  \"protanStrong\": null,\n" +
            "  \"deutanStrong\": null,\n" +
            "  \"total\":null, \n" +
            "  \"extra\":false    \n" +
            " },\n" +
            "  {\n" +
            "  \"id\":12,   \n" +
            "  \"filename\":\"12.gif\",\n" +
            "  \"normal\":16,\n" +
            "  \"protanStrong\": null,\n" +
            "  \"deutanStrong\": null,\n" +
            "  \"total\":null, \n" +
            "  \"extra\":false    \n" +
            " },\n" +
            "  {\n" +
            "  \"id\":13,   \n" +
            "  \"filename\":\"13.gif\",\n" +
            "  \"normal\":73,\n" +
            "  \"protanStrong\": null,\n" +
            "  \"deutanStrong\": null,\n" +
            "  \"total\":null, \n" +
            "  \"extra\":false    \n" +
            " },\n" +
            "  {\n" +
            "  \"id\":14,   \n" +
            "  \"filename\":\"14.gif\",\n" +
            "  \"normal\":null,\n" +
            "  \"protanStrong\": 5,\n" +
            "  \"deutanStrong\": 5,\n" +
            "  \"total\":null, \n" +
            "  \"extra\":false    \n" +
            " },\n" +
            "  {\n" +
            "  \"id\":15,   \n" +
            "  \"filename\":\"15.gif\",\n" +
            "  \"normal\":null,\n" +
            "  \"protanStrong\": 45,\n" +
            "  \"deutanStrong\": 45,\n" +
            "  \"total\":null, \n" +
            "  \"extra\":false    \n" +
            " },\n" +
            "  {\n" +
            "  \"id\":16,   \n" +
            "  \"filename\":\"16.gif\",\n" +
            "  \"normal\":26,\n" +
            "  \"protanStrong\": 6,\n" +
            "  \"deutanStrong\": 2,\n" +
            "  \"total\":null, \n" +
            "  \"extra\":true    \n" +
            " },\n" +
            "   {\n" +
            "  \"id\":17,   \n" +
            "  \"filename\":\"17.gif\",\n" +
            "  \"normal\":42,\n" +
            "  \"protanStrong\": 2,\n" +
            "  \"deutanStrong\": 4,\n" +
            "  \"total\":null, \n" +
            "  \"extra\":true    \n" +
            " }\n" +
            "]";
    private static List<IshiharaPlate> ishiharaPlates;
    private static final IshiharaThreshold ishiharaThreshold = new IshiharaThreshold();

    /**
     * Generate the default values for this test.
     */
    static {
        Gson gson = new Gson();
        final Type type = new TypeToken<List<IshiharaPlate>>() {
        }.getType();
        ishiharaPlates = gson.fromJson(platesJson, type);

        ishiharaThreshold.setNormal(13);
        ishiharaThreshold.setDeficiency(9);
    }

    /**
     * Test for normal vision.
     */
    @Test
    public void normalTest() {
        Integer[] res = {12, 8, 29, 5, 3, 15, 74, 6, 45, 5, 7, 16, 73, null, null, 26, 42};
        int result = IshiharaCalculateResults.getResult(ishiharaThreshold, this.generateResultList(res));
        assertEquals(IshiharaCalculateResults.NORMAL, result);
    }

    /**
     * Test for General Red/Green deficiency.
     */
    @Test
    public void generalRGTest() {
        Integer[] res = {12, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null};
        int result = IshiharaCalculateResults.getResult(ishiharaThreshold, this.generateResultList(res));
        assertEquals(IshiharaCalculateResults.GENERAL_READ_GREEN, result);
    }

    /**
     * Second test for General Red/Green deficiency.
     */
    @Test
    public void deutanRGTest2() {
        Integer[] res = {12, 3, 70, 2, 5, 17, 21, null, null, null, null, null, null, 5, 45, 26, 42};
        int result = IshiharaCalculateResults.getResult(ishiharaThreshold, this.generateResultList(res));
        assertEquals(IshiharaCalculateResults.GENERAL_READ_GREEN, result);
    }

    /**
     * Test for deutan vision.
     */
    @Test
    public void deutanRGTest() {
        Integer[] res = {12, 3, 70, 2, 5, 17, 21, null, null, null, null, null, null, 5, 45, 2, 4};
        int result = IshiharaCalculateResults.getResult(ishiharaThreshold, this.generateResultList(res));
        assertEquals(IshiharaCalculateResults.DEUTAN, result);
    }

    /**
     * Test for protan vision.
     */
    @Test
    public void protanRGTest() {
        Integer[] res = {12, 3, 70, 2, 5, 17, 21, null, null, null, null, null, null, 5, 45, 6, 2};
        int result = IshiharaCalculateResults.getResult(ishiharaThreshold, this.generateResultList(res));
        assertEquals(IshiharaCalculateResults.PROTAN, result);
    }

    /**
     * Test for uncertain vision.
     */
    @Test
    public void uncertainTest() {
        Integer[] res = {12, 3, 70, 2, 5, 17, 21, 6, 45, 5, 7, 16, 73, null, null, 26, 42};
        int result = IshiharaCalculateResults.getResult(ishiharaThreshold, this.generateResultList(res));
        assertEquals(IshiharaCalculateResults.UNCERTAIN, result);
    }

    /**
     * Creates a list of IshiharaResult object for from a array of result Integers.
     *
     * @param res array of results
     * @return list of IshiharaResult objects.
     */
    private List<IshiharaResult> generateResultList(Integer... res) {
        List<IshiharaResult> results = new ArrayList<>();

        for (int i = 0; i < ishiharaPlates.size(); i++) {
            results.add(new IshiharaResult(ishiharaPlates.get(i), res[i]));
        }

        return results;
    }


}

