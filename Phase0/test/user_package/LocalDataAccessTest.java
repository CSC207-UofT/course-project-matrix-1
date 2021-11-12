package user_package;

import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

public class LocalDataAccessTest {
    Map<String, User> userMap;
    Map<String, History> historyMap;
    LocalDataAccess dataSource;

    @Before
    public void setUp() {
        // Example User
        User exampleUser = new User("guest", "Guest", 20, "Student");
        userMap = new HashMap<>();
        userMap.put("guest", exampleUser);

        // Example History
        HashMap<String, Object> record = new HashMap<>();

        HashMap<String, Object> equationDetails = new HashMap<>();
        equationDetails.put("numOfEquations", 100);
        equationDetails.put("negAllowed", false);
        equationDetails.put("operator", "+");

        HashMap<String, Object> formatDetails = new HashMap<>();
        formatDetails.put("numColumns", 4);
        formatDetails.put("numRows", 25);

        record.put("worksheetKey", "0");
        record.put("equationDetails", equationDetails);
        record.put("formatDetails", formatDetails);

        History exampleHistory = new History();
        exampleHistory.addWorksheetRecord(record);

        historyMap = new HashMap<>();
        historyMap.put("guest", exampleHistory);

        dataSource = new LocalDataAccess();
    }

    @Test
    public void storeAndGetUsers() throws Exception {
        dataSource.storeUsers(userMap);
        Map<String, User> usersRetrieved = dataSource.getUsers();

        assertEquals("Student", usersRetrieved.get("guest").getDetails().get("role"));
    }

    @Test
    public void storeAndGetHistories() throws ClassNotFoundException {
        dataSource.storeHistories(historyMap);
        Map<String, History> historiesRetrieved = dataSource.getHistories();
        assertNotNull(historiesRetrieved.get("guest").findWorksheetRecord("0"));
    }
}