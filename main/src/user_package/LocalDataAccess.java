package user_package;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

/**
 * LocalDataAccess class. Implements local data retrieval and storage.
 *
 * @author Stanley Hua
 * @version 1.0
 */
public class LocalDataAccess implements DataAccessInterface {
    final String dataFolder = "main/src/user_package/users_data";

    /**
     * Stores locally a map of username to User objects.
     *
     * @param existingUsers map of existing Users
     */
    public void storeUsers(Map<String, User> existingUsers) {
        try {
            // Check if parent directory exists
            makeParentDirectory(dataFolder);

            FileOutputStream usersOut = new FileOutputStream(dataFolder + "/users.ser");
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
            // Check if parent directory exists
            makeParentDirectory(dataFolder);

            FileOutputStream historiesOut = new FileOutputStream(dataFolder + "/history.ser");
            ObjectOutputStream out = new ObjectOutputStream(historiesOut);
            out.writeObject(existingHistories);
            out.close();
            historiesOut.close();
        } catch (IOException i) {
            i.printStackTrace();
            System.out.println(existingHistories instanceof Serializable);
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
            // Check if parent directory exists
            makeParentDirectory(dataFolder);

            // Attempt to read from file
            FileInputStream usersIn = new FileInputStream(dataFolder + "/users.ser");
            ObjectInputStream in = new ObjectInputStream(usersIn);
            Map<String, User> existingUsers = (Map<String, User>) in.readObject();
            in.close();
            usersIn.close();
            return existingUsers;
        } catch (FileNotFoundException i) {
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
            // Check if parent directory exists
            makeParentDirectory(dataFolder);

            FileInputStream historiesIn = new FileInputStream(dataFolder + "/history.ser");
            ObjectInputStream in = new ObjectInputStream(historiesIn);
            Map<String, History> existingHistories = (Map<String, History>) in.readObject();
            in.close();
            historiesIn.close();
            return existingHistories;
        } catch (FileNotFoundException i) {
            return new HashMap<>();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            throw new ClassNotFoundException();
        }
    }


    /**
     * Helper method to create parent directory, where user data will be stored.
     *
     * @param path where to store user and history data
     * @throws FileNotFoundException when folder could not be made successfully
     */
    public void makeParentDirectory(String path) throws FileNotFoundException {
        File file = new File(path);
        if (!(file.exists() && file.isDirectory())) {
            boolean fileMade = file.mkdir();
            if (!fileMade) {
                throw new FileNotFoundException("users_data directory could not be made!");
            }
        }
    }
}
