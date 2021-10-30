package worksheet_maker;

import equation_builders.*;
import equation_entities.InvalidInputException;

import java.util.HashMap;

/**
 * Generates a worksheet through the WorksheetInput interface.
 *
 * @author Sean Jeong
 * @version 1.0
 * @since 2021-10-24.
 */
public class WorksheetGenerator {
    /**
     * Creates all the equations in the worksheet using various worksheet related parameters.
     *
     * @param numOfEquations the number of equations in this worksheet.
     * @param operator       the operator in the equations of the worksheet.
     * @param operandRange1  the absolute range of values that the first operand can be.
     * @param operandRange2  the absolute range of values that the second operand can be.
     * @param negAllowed     specifies if the operands are allowed to be negative.
     */
    //Worksheet input rather than Worksheet
    private final WorksheetInput ws;

    public WorksheetGenerator(WorksheetInput ws){
        this.ws = ws;
    }
    //TODO: change this to hashmap input instead of parameter list
    public void createWorksheet(HashMap<String, Object> equationDetails) {
        //Parse equationDetails for parameters
        int numOfEquations = (int) equationDetails.get("numOfEquations");
        char operator = (char) equationDetails.get("operator");
        int[] operandRange1 = (int[]) equationDetails.get("operandRange1");
        int[] operandRange2 = (int[]) equationDetails.get("operandRange2");
        boolean negAllowed = (boolean) equationDetails.get("negAllowed");

        BedmasEquationDirector bedmasEquationDirector = new BedmasEquationDirector();

        //Create and assign the correct BedmasEquationBuilder.
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
        //Construct the specified number of Equations and save them in Worksheet.
        for (int i = 0; i < numOfEquations; i++) {
            bedmasEquationDirector.constructBedmasEquation(operandRange1, operandRange2, negAllowed);
            this.ws.addEquation(bedmasEquationDirector.getBedmasEquation());
        }

    }


}
