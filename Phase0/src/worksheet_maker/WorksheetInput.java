package worksheet_maker;

import equation_entities.Equation;

/**
 * Assigns equations to a Worksheet.
 *
 * @author Will Jeong
 * @version 1.0
 * @since 2021-10-25.
 */
public interface WorksheetInput {
    public void addEquation(Equation e);
}
