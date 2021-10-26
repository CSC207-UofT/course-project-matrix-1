package worksheet_maker;

/**
 * Assigns equations to a Worksheet.
 *
 * @author Will Jeong
 * @version 1.0
 * @since 2021-10-25.
 */
public interface WorksheetInput {
    /**
     * Adds a new equation to Worksheet.
     *
     * @param e the Equation to be added to Worksheet.
     */
    public void addEquation(Equation e);
}
