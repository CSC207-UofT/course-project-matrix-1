package userPackage;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

/**
 * LocalDataAccess class. Implements local data retrieval and storage.
 * 
 * @version 1.0
 * @authors Stanley Hua
 */
public class LocalDataAccess implements DataAccessInterface {

    /**
     * Stores list of Users.
     * @param existingUsers list of existing Users
     */
    public static void storeUsers(Map<String, User> existingUsers){
        try {
            // TODO: Remove Phase0
            FileOutputStream usersOut = new FileOutputStream("Phase0/src/userPackage/usersData/users.ser");
            ObjectOutputStream out = new ObjectOutputStream(usersOut);
            out.writeObject(existingUsers);
            out.close();
            usersOut.close();
        } catch (IOException i){    // if IO exception occurs, print exception details
            i.printStackTrace();
        }
    }

    /**
     * Stores all existing users' worksheet history.
     */
    public static void storeHistories(Map<String, History> existingHistories){
        try {
            // TODO: Remove Phase0
            FileOutputStream historiesOut = new FileOutputStream("Phase0/src/userPackage/usersData/history.ser");
            ObjectOutputStream out = new ObjectOutputStream(historiesOut);
            out.writeObject(historiesOut);
            out.close();
            historiesOut.close();
        } catch (IOException i){    // if IO exception occurs, print exception details
            i.printStackTrace();
        }
    }

    /**
     * Retrieves list of Users.
     * @return list of Users
     */
    public static Map<String, User> getUsers(){
        try {
            // Attempt to read from file
            FileInputStream usersIn = new FileInputStream("Phase0/src/userPackage/usersData/users.ser");
            ObjectInputStream in = new ObjectInputStream(usersIn);
            Map<String, User> existingUsers = (HashMap<String, User>) in.readObject();
            in.close();
            usersIn.close();
            return existingUsers;
        } catch (FileNotFoundException i) {
//            return new ArrayList<>();
        } catch (IOException | ClassNotFoundException e) {
//            e.printStackTrace();
//            return new ArrayList<>();
        }
        // TODO: Remove
        // For testing purposes, return artificial User main.
        Map<String, User> exampleExistingUsers = new HashMap<>();
        User user1 = new User("main", "MainUser", 21, "Student");  // default user for testing
        exampleExistingUsers.put("main", user1);
        return exampleExistingUsers;
    }

    /**
     * Retrieves all existing users' worksheet history.
     * @return mapping of username to History
     */
    public static Map<String, History> getHistories(){
        try {
            // Attempt to read from file
            FileInputStream historiesIn = new FileInputStream("Phase0/src/userPackage/usersData/history.ser");
            ObjectInputStream in = new ObjectInputStream(historiesIn);
            Map<String, History> existingHistories = (Map<String, History>) in.readObject();
            in.close();
            historiesIn.close();
            return existingHistories;
        } catch (FileNotFoundException i) {
//            return new ArrayList<>();
        } catch (IOException | ClassNotFoundException e) {
//            e.printStackTrace();
//            return new ArrayList<>();
        }
        // TODO: Remove
        // For testing purposes, return artificial History Manager.
        Map<String, History> exampleHistoryManager = new HashMap<>();
        History exampleHistory = new History();
        Map<String, Object> worksheetDetails = new HashMap<>();
        worksheetDetails.put("numQuestions", 100);
        worksheetDetails.put("worksheetKey", "1");
        exampleHistory.addWorksheetRecord(worksheetDetails);
        exampleHistoryManager.put("main", exampleHistory);

        return exampleHistoryManager;
    }


    public static void main(String[] args) {
        Map<String, User> users = getUsers();
        Map<String, History> histories = getHistories();
        storeUsers(users);
        storeHistories(histories);
    }
}
