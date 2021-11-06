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
    private final List<Equation> equations = new ArrayList<>();

    /**
     * Adds a new equation to Worksheet.
     *
     * @param e the Equation to be added to Worksheet.
     */
    @Override
    public void addEquation(Equation e) {
        equations.add(e);
    }

    /**
     * Returns a String representation of a Worksheet.
     *
     * @return an array of equations arrays, where each symbol in the equation array is a separate term.
     * Ex. [5, +, 4, =, 9]
     */
    @Override
    public String[][] equationsToStringArray() {
        String[][] equationsString = new String[equations.size()][];
        for (int i = 0; i < equations.size(); i++) {
            equationsString[i] = equations.get(i).getEquation().toArray(new String[0]);
        }
        return equationsString;
    }

    /**
     * Returns the number of questions in the worksheet.
     *
     * @return the number of questions in the worksheet.
     */
    @Override
    public int getQuestionNumber() {
        return equations.size();
    }

    public List<Equation> getEquations() {
        return equations;
    }
}
