package worksheet_maker;

/**
 * Retrieves an array of equations and the equation number from Worksheet.
 *
 * @author Will Jeong
 * @version 1.0
 * @since 2021-10-25.
 */
interface WorksheetOutput {

    /**
     * Returns a String representation of a Worksheet.
     *
     * @return an array of equations arrays, where each symbol in the equation array is a separate term.
     * Ex. [5, +, 4, =, 9]
     */
    String[][] equationsToStringArray();

    /**
     * Returns the number of questions in the Worksheet.
     *
     * @return the number of questions in the Worksheet.
     */
    int getQuestionNumber();
}
