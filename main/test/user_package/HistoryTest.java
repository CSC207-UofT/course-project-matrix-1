package user_package;

import equation_parameters.EquationDetails;
import equation_parameters.WholeNumEquationDetails;
import exceptions.InvalidInputException;
import exceptions.RecordDoesNotExistException;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

public class HistoryTest {
    History historyExample;

    @Before
    public void setUp() {
        // Set up EquationDetails
        EquationDetails equationDetails = new WholeNumEquationDetails();
        equationDetails.setNumOfEquations(100);

        this.historyExample = new History();
        Map<String, Object> testRecord1 = new HashMap<>();
        testRecord1.put("worksheetKey", "1");
        testRecord1.put("equationDetails", equationDetails);
        historyExample.addWorksheetRecord(testRecord1);
    }

    /**
     * Tests getWorksheetHistory method.
     * - Checks that current worksheet history is not empty.
     */
    @Test
    public void testGetWorksheetHistory() {
        assertTrue(historyExample.getWorksheetHistory().size() > 0);
    }

    /**
     * Tests findRecordInHistory method.
     * - Checks to see if record in historyExample can be found.
     */
    @Test
    public void testFindRecordInHistoryPresent() {
        try {
            assertNotNull(historyExample.findWorksheetRecord("1"));
        } catch (RecordDoesNotExistException e) {
            e.printStackTrace();
            fail();
        }
    }

    /**
     * Tests findRecordInHistory method.
     * - Checks to see if exception is raised when specified worksheet does not exist.
     */
    @Test(expected = RecordDoesNotExistException.class)
    public void testFindRecordInHistoryAbsent() throws RecordDoesNotExistException {
        historyExample.findWorksheetRecord("This is an invalid key");
    }

    /**
     * Tests addWorksheetRecord method.
     * - Checks to see if worksheet is added.
     * - Assumes findRecordInHistory is working.
     */
    @Test
    public void testAddWorksheetRecord() {
        Map<String, Object> testRecord2 = new HashMap<>();
        testRecord2.put("worksheetKey", "2");
        historyExample.addWorksheetRecord(testRecord2);

        try {
            assertNotNull(historyExample.findWorksheetRecord("2"));
        } catch (RecordDoesNotExistException e) {
            e.printStackTrace();
            fail();
        }
    }

    /**
     * Tests removeWorksheetRecord method.
     * - If worksheet is successfully removed, findRecordInHistory should throw an exception.
     * - Assumes addWorksheetRecord and findRecordInHistory are working.
     */
    @Test(expected = RecordDoesNotExistException.class)
    public void testRemoveWorksheetRecord() throws RecordDoesNotExistException {
        // Add dummy record
        Map<String, Object> testRecord3 = new HashMap<>();
        testRecord3.put("worksheetKey", "3");

        historyExample.addWorksheetRecord(testRecord3);

        // Try to remove record
        try {
            historyExample.removeWorksheetRecord("3");
        } catch (RecordDoesNotExistException e) {
            e.printStackTrace();
            fail();
        }
        historyExample.findWorksheetRecord("3");
    }

    /**
     * Tests setScore method. If successful, the score should be the same in worksheet record.
     */
    @Test
    public void testSetScorePerfect() throws RecordDoesNotExistException {
        historyExample.setScore("1", 100);
        assertEquals(100, historyExample.findWorksheetRecord("1").get("score"));
    }

    @Test(expected = InvalidInputException.class)
    public void testSetScoreNegative() throws RecordDoesNotExistException {
        historyExample.setScore("1", -1);
    }

    @Test(expected = InvalidInputException.class)
    public void testSetScoreOver() throws RecordDoesNotExistException {
        historyExample.setScore("1", 101);
    }
}