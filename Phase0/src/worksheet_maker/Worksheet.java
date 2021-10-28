package worksheet_maker;

import equation_entities.Equation;

import java.util.ArrayList;
import java.util.List;

/**
 * Holds a list of all the Equations.
 *
 * @author Sean Jeong
 * @version 1.0
 * @since 2021-10-24.
 */
public class Worksheet implements WorksheetInput, WorksheetOutput {
    /**
     * This Worksheet's list of equations.
     */
    private List<Equation> equations = new ArrayList<>();

    @Override
    public void addEquation(Equation e) {
        equations.add(e);
    }

    @Override
    public String[][] equationsToStringArray() {
        //TODO: worry about corner case where there is nothing in equations
        //Create an empty 2D list to store the equations as String[].
        String[][] equationsString = new String[equations.size()][];
        //Loops through its own equation to generate String[] of them.
        for (int i = 0; i < equations.size(); i++) {
            equationsString[i] = equations.get(i).getEquation();
        }
        return equationsString;
    }

}
