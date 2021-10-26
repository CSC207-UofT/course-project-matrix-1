package worksheet_maker;

/**
 * Generates a Worksheet.
 *
 * @author Sean Jeong
 * @version 1.0
 * @since 2021-10-24.
 */
public class WorksheetGenerator {
    public WorksheetInput createWorksheet(int numOfEquations, char operator, int[] operandRange1, int[] operandRange2, boolean negAllowed) {
        WorksheetInput ws = new Worksheet();
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
            //TODO: improve the exception.
            throw new RuntimeException("There shouldn't be any other symbol here");
        }
        //Construct the specified number of Equations and save them in Worksheet.
        for (int i = 0; i < numOfEquations; i++) {
            bedmasEquationDirector.constructBedmasEquation(operandRange1, operandRange2, negAllowed);
            ws.addEquation(bedmasEquationDirector.getBedmasEquation());
        }
        return ws;
    }

}
