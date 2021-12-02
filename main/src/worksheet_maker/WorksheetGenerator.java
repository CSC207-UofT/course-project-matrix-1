package worksheet_maker;

import equation_builders.EquationDirector;
import equation_builders.StandardEquationDirector;
import equation_parameters.EquationDetails;
import equation_parameters.FractionAddSubEquationDetails;
import equation_parameters.FractionMultiDivEquationDetails;
import equation_parameters.WholeNumEquationDetails;
import exceptions.InvalidInputException;
import utilities.Randomizer;

import static constants.EquationType.FRACTION;
import static constants.EquationType.WHOLE_NUMBER;

/**
 * Generates a worksheet through the WorksheetInput interface.
 *
 * @author Sean Jeong
 * @version 1.0
 * @since 2021-10-24.
 */
public class WorksheetGenerator {
    private final WorksheetInput worksheet;
    private final Randomizer randomizer;

    /**
     * @param worksheet Worksheet input
     * @param seed      Random seed used to fix randomness in worksheet generation
     */
    public WorksheetGenerator(WorksheetInput worksheet, int seed) {
        this.worksheet = worksheet;
        randomizer = new Randomizer(seed);
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
        EquationDirector equationDirector;
        //Create and assign the appropriate builder to a director.
        if (equationDetails instanceof WholeNumEquationDetails) {
            equationDirector = new StandardEquationDirector(randomizer, equationDetails, WHOLE_NUMBER);
        } else if (equationDetails instanceof FractionAddSubEquationDetails || equationDetails instanceof FractionMultiDivEquationDetails) {
            equationDirector = new StandardEquationDirector(randomizer, equationDetails, FRACTION);
        } else {
            throw new InvalidInputException();
        }
        for (int i = 0; i < equationDetails.getNumOfEquations(); i++) {
            equationDirector.constructEquation();
            this.worksheet.addEquation(equationDirector.getEquation());
        }
    }
}
