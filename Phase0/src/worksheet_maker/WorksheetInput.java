package worksheet_maker;

import equation_entities.Equation;

/**
 * Sequentially assigns equations to a Worksheet.
 *
 * @author Will Jeong
 * @version 1.0
 * @since 2021-10-25.
 */
interface WorksheetInput {
    /**
     * Adds a new equation to Worksheet.
     *
     * @param e the Equation to be added to Worksheet.
     */
    void addEquation(Equation e);
}
