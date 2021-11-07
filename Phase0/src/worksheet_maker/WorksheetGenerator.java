package worksheet_maker;

import equation_builders.*;

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
     * Populates the worksheet with equations that adhere to the various equation related parameters found in
     * equationDetails.
     *
     * @param equationDetails Hashmap showing details necessary for equation generation. Includes numOfEquation,
     *                        operator, operandRange1, operandRange2, negAllowed.
     */
    public void populateWorksheet(Map<String, Object> equationDetails) {
        //Create and assign the appropriate builder to a director.
        WholeBedmasDirector wholeBedmasDirector = getWholeBedmasDirector((char) equationDetails.get("operator"));
        for (int i = 0; i < (int) equationDetails.get("numOfEquations"); i++) {
            wholeBedmasDirector.constructBedmasEquation((int[]) equationDetails.get("operandRange1"),
                    (int[]) equationDetails.get("operandRange2"), (boolean) equationDetails.get("negAllowed"));
            this.worksheet.addEquation(wholeBedmasDirector.getBedmasEquation());
        }
    }


    /**
     * Creates a BedmasEquationBuilder according to the operator and assigns it to BedmasEquationDirector, which it
     * returns.
     *
     * @param operator the operator in the equations of the worksheet.
     * @return WholeBedmasDirector with the correct WholeBedmasBuilder set to it.
     */
    private WholeBedmasDirector getWholeBedmasDirector(char operator) {
        WholeBedmasDirector wholeBedmasDirector = new WholeBedmasDirector();
        wholeBedmasDirector.setBedmasEquationBuilder(operator);
        return wholeBedmasDirector;
    }
}
