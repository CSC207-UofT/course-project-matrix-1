package worksheet_maker;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Retrieves an array of equations and the equation number from Worksheet.
 *
 * @author Will Jeong
 * @version 1.0
 * @since 2021-10-25.
 */
interface WorksheetOutput {
    /**
     * Returns a list of each equation (a 'worksheet') each as a Hashmap<String, String>.
     *
     * @return a list of each equation as a Hashmap<String, String>.
     */
    public List<Map<String, String>> worksheetToHashMapList();

    /**
     * Returns the number of questions in the Worksheet.
     *
     * @return the number of questions in the Worksheet.
     */
    int getQuestionNumber();
}
