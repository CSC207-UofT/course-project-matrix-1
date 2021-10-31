package worksheet_maker;

import equation_builders.*;
import equation_entities.InvalidInputException;

import java.util.Map;

/**
 * Generates a worksheet through the WorksheetInput interface.
 *
 * @author Sean Jeong
 * @version 1.0
 * @since 2021-10-24.
 */
public class WorksheetGenerator {
    //Worksheet input rather than Worksheet
    private final WorksheetInput worksheet;

    public WorksheetGenerator(WorksheetInput worksheet) {
        this.worksheet = worksheet;
    }

    /**
     * Creates all the equations in the worksheet using various equation related parameters found in equationDetails.
     *
     * @param equationDetails Hashmap showing details necessary for equation generation. Includes numOfEquation,
     *                        operator, operandRange1, operandRange2, negAllowed.
     */
    public void createWorksheet(Map<String, Object> equationDetails) {
        //Parse equationDetails
        int numOfEquations = (int) equationDetails.get("numOfEquations");
        char operator = (char) equationDetails.get("operator");
        int[] operandRange1 = (int[]) equationDetails.get("operandRange1");
        int[] operandRange2 = (int[]) equationDetails.get("operandRange2");
        boolean negAllowed = (boolean) equationDetails.get("negAllowed");

        //Create and assign the appropriate builder to a director.
        BedmasEquationDirector bedmasEquationDirector = getBedmasEquationDirector(operator);

        //Use the director and parameters to add Equations to the previously empty worksheet
        createEquationsInWorksheet(numOfEquations, operandRange1, operandRange2, negAllowed, bedmasEquationDirector);
    }

    /**
     * Creates a numOfEquations number of Equations with the following parameters in worksheet and using the operator
     * found within the BedmasEquationDirector.
     *
     * @param numOfEquations the number of equations in this worksheet.
     * @param operandRange1  the absolute range of values that the first operand can be.
     * @param operandRange2  the absolute range of values that the second operand can be.
     * @param negAllowed     specifies if the operands are allowed to be negative.
     */
    private void createEquationsInWorksheet(int numOfEquations, int[] operandRange1, int[] operandRange2, boolean negAllowed, BedmasEquationDirector bedmasEquationDirector) {
        for (int i = 0; i < numOfEquations; i++) {
            bedmasEquationDirector.constructBedmasEquation(operandRange1, operandRange2, negAllowed);
            this.worksheet.addEquation(bedmasEquationDirector.getBedmasEquation());
        }
    }

    /**
     * Creates a BedmasEquationBuilder according to the operator and assigns it to BedmasEquationDirector, which it
     * returns.
     *
     * @param operator the operator in the equations of the worksheet.
     * @return BedmasEquationDirector with the correct BedmasEquationBuilder set to it.
     */
    private BedmasEquationDirector getBedmasEquationDirector(char operator) {
        BedmasEquationDirector bedmasEquationDirector = new BedmasEquationDirector();

        char ADD = '+';
        char SUBTRACT = '-';
        char MULTIPLY = '*';
        char DIVIDE = '/';

        if (operator == ADD) {
            bedmasEquationDirector.setBedmasEquationBuilder(new AddBedmasEquationBuilder());
        } else if (operator == SUBTRACT) {
            bedmasEquationDirector.setBedmasEquationBuilder(new SubBedmasEquationBuilder());
        } else if (operator == MULTIPLY) {
            bedmasEquationDirector.setBedmasEquationBuilder(new MultiplyBedmasEquationBuilder());
        } else if (operator == DIVIDE) {
            bedmasEquationDirector.setBedmasEquationBuilder(new DivideBedmasEquationBuilder());
        } else {
            throw new InvalidInputException();
        }
        return bedmasEquationDirector;
    }
}
