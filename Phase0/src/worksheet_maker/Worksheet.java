package worksheet_maker;

import java.util.ArrayList;
import java.util.List;

/**
 * Holds a list of all the Equations.
 *
 * @author Sean Jeong
 * @version 1.0
 * @since 2021-10-24.
 */
public class Worksheet {
    //TODO add WorksheetDataSetter and WorksheetDataGetter interfaces for ISP
    //TODO: Consider resetting worksheet
    private final static List<Equation> equations = new ArrayList<>();

    //Used by WorksheetGenerator
    public static void addEquation(Equation e) {
        equations.add(e);
    }

    //Used by PDFPresenter
    public static String[][] equationsToStringArray() {
        //TODO: worry about corner case where there is nothing in equations
        String[][] equationsString = new String[equations.size()][];
        for (int i = 0; i < equations.size(); i++) {
            equationsString[i] = new String[]{equations.get(i).questionToString(), equations.get(i).answerToString()};
        }
        return equationsString;
    }

}
