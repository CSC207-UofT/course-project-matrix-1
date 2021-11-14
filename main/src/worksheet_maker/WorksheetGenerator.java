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
    private final WorksheetInput worksheet;
    private final int seed;

    /**
     * @param worksheet     Worksheet input
     * @param seed          Random seed used to fix randomness in worksheet generation
     */
    public WorksheetGenerator(WorksheetInput worksheet, int seed) {
        this.worksheet = worksheet;
        this.seed = seed;
    }

    /**
     * Populates the worksheet with equations that adhere to the various equation related parameters found in
     * equationDetails.
     *
     * Initial equation takes in worksheet seed. Succeeding equations will take on fixed increments of the seed. This
     * allows later reconstruction of the worksheet given only one worksheet seed.
     *
     * @param equationDetails Hashmap showing details necessary for equation generation. Includes numOfEquation,
     *                        operator, operandRange1, operandRange2, negAllowed.
     */
    public void populateWorksheet(Map<String, Object> equationDetails) {
        //Create and assign the appropriate builder to a director.
        WholeBedmasDirector wholeBedmasDirector = getWholeBedmasDirector((char) equationDetails.get("operator"));

        // Update worksheet random seed per question.
        int currentSeed = this.seed;

        for (int i = 0; i < (int) equationDetails.get("numOfEquations"); i++) {
            wholeBedmasDirector.constructBedmasEquation((int[]) equationDetails.get("operandRange1"),
                    (int[]) equationDetails.get("operandRange2"), (boolean) equationDetails.get("negAllowed"),
                    currentSeed);
            this.worksheet.addEquation(wholeBedmasDirector.getBedmasEquation());
            currentSeed += 100;
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
