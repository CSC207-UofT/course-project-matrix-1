package user_package;

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
     * Stores locally a map of username to User objects.
     *
     * @param existingUsers map of existing Users
     */
    public void storeUsers(Map<String, User> existingUsers) {
        try {
            // TODO: Remove Phase0
            FileOutputStream usersOut = new FileOutputStream("Phase0/src/user_package/users_data/users.ser");
            ObjectOutputStream out = new ObjectOutputStream(usersOut);
            out.writeObject(existingUsers);
            out.close();
            usersOut.close();
        } catch (IOException i) {
            i.printStackTrace();
        }
    }

    /**
     * Stores locally a mapping to existing users' worksheet history.
     */
    public void storeHistories(Map<String, History> existingHistories) {
        try {
            // TODO: Remove Phase0
            FileOutputStream historiesOut = new FileOutputStream("Phase0/src/user_package/users_data/history.ser");
            ObjectOutputStream out = new ObjectOutputStream(historiesOut);
            out.writeObject(existingHistories);
            out.close();
            historiesOut.close();
        } catch (IOException i) {
            i.printStackTrace();
        }
    }

    /**
     * Retrieves map of username to Users.
     *
     * @return list of Users
     */
    @SuppressWarnings("unchecked")
    public Map<String, User> getUsers() throws Exception {
        try {
            // Attempt to read from file
            FileInputStream usersIn = new FileInputStream("Phase0/src/user_package/users_data/users.ser");
            ObjectInputStream in = new ObjectInputStream(usersIn);
            Map<String, User> existingUsers = (HashMap<String, User>) in.readObject();
            in.close();
            usersIn.close();
            return existingUsers;
        } catch (FileNotFoundException i) {
            i.printStackTrace();
            return new HashMap<>();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            throw new ClassNotFoundException();
        }
    }

    /**
     * Retrieves mapping to existing users' worksheet history stored locally.
     *
     * @return mapping of username to History
     */
    @SuppressWarnings("unchecked")
    public Map<String, History> getHistories() throws Exception {
        try {
            FileInputStream historiesIn = new FileInputStream("Phase0/src/user_package/users_data/history.ser");
            ObjectInputStream in = new ObjectInputStream(historiesIn);
            Map<String, History> existingHistories = (Map<String, History>) in.readObject();
            in.close();
            historiesIn.close();
            return existingHistories;
        } catch (FileNotFoundException i) {
            return new HashMap<>() {
            };
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            throw new ClassNotFoundException();
        }
    }

}
