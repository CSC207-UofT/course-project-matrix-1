package worksheet_maker;

import equation_builders.StandardEquationDirector;
import equation_builders.EquationDirector;
import equation_parameters.*;
import static constants.EquationType.*;

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
     * @param worksheet Worksheet input
     * @param seed      Random seed used to fix randomness in worksheet generation
     */
    public WorksheetGenerator(WorksheetInput worksheet, int seed) {
        this.worksheet = worksheet;
        this.seed = seed;
    }

    /**
     * Populates the worksheet with equations that adhere to the various equation related parameters found in
     * equationDetails.
     * <p>
     * Initial equation takes in worksheet seed. Succeeding equations will take on fixed increments of the seed. This
     * allows later reconstruction of the worksheet given only one worksheet seed.
     *
     * @param equationDetails Hashmap showing details necessary for equation generation. Includes numOfEquation,
     *                        operator, operandRange1, operandRange2, negAllowed.
     */
    public void populateWorksheet(EquationDetails equationDetails) {
        EquationDirector equationDirector = null;
        //TODO: fix this to not be null
        //Create and assign the appropriate builder to a director.
        if (equationDetails instanceof WholeNumEquationDetails) {
            equationDirector = new StandardEquationDirector(WHOLE_NUMBER, equationDetails);
        } else if (equationDetails instanceof FractionAddSubEquationDetails || equationDetails instanceof FractionMultiDivEquationDetails) {
            equationDirector = new StandardEquationDirector(FRACTION, equationDetails);
        } else if (equationDetails instanceof DecimalEquationDetails) {
            // TODO: Not yet implemented
            throw new RuntimeException("Decimal Standard Equations Not Implemented!");
//            equationDirector = new StandardEquationDirector(DECIMAL);
        }
        assert equationDirector != null;

        // Update worksheet random seed per question.
        int currentSeed = this.seed;

        for (int i = 0; i < equationDetails.getNumOfEquations(); i++) {
            equationDirector.constructEquation(equationDetails, currentSeed);
            this.worksheet.addEquation(equationDirector.getEquation());
            currentSeed += 100;
        }
    }
}
