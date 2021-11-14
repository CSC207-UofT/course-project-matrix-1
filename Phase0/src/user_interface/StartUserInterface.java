//TODO: Modify UI to use WorksheetController (use setWorksheet) and PDFPresenter (createWorksheetPDF)
//import worksheet_maker.PDFPresenter;
//import worksheet_maker.Worksheet;
//import worksheet_maker.WorksheetGenerator;
//
//import java.util.HashMap;
//import java.util.Objects;
//import java.util.Scanner;
//
///**
// * A User interface. Can interact directly with the user, converts input into method calls for higher level code.
// *
// * @author Ethan Ing, Piotr Pralat, Sean Jeong, Will Jeong
// * @version 1.0
// * @since 2021-10-13
// */
//public class UserInterface {
//
//    private final String equationType;
//    private final int numEquations;
//    private final String difficulty;
//    private final int fontSize;
//    private final String worksheetTitle;
//    private final String equationFormat;
//    private final WorksheetGenerator wGenerator = new WorksheetGenerator();
//    private final PDFPresenter pPresenter = new PDFPresenter();
//    private final UserController userController = new UserController();
//
//    public UserInterface() {
//        Scanner sc = new Scanner(System.in);
//
//        // Welcome and prompt user for their username.
//        System.out.println("Welcome to Matrix!");
//        userController.setCurrentUsername(getUsername());
//
//        System.out.println("Welcome " + userController.getCurrentUsername());
//
//        // Ask if the user would like to create a new worksheet, user profile TODO: or view user history (currently at bottom)
//        menuSelectChoice();
//        System.out.println("Create a new worksheet");
//
//        // Get the topic the user is interested in.
//        System.out.println("Choose a topic (you can only use 'standard add'): ");
//        this.equationType = sc.nextLine();
//
//        // Get the difficult level of the topic.
//        this.difficulty = chooseDifficulty();
//
//        // Get the number of questions.
//        System.out.println("Choose the number of questions: ");
//        this.numEquations = sc.nextInt();
//
//        Scanner sct = new Scanner(System.in);
//
//        // Let user customize the page details.
//        System.out.println("Choose the worksheet title: ");
//        this.worksheetTitle = sct.nextLine();
//        System.out.println("Choose the font size?");
//        this.fontSize = sct.nextInt();
//        this.equationFormat = chooseQuestionFormat();
//
//        finalPage();
//
//    }
//
//    private void menuSelectChoice() {
//        Scanner sc = new Scanner(System.in);
//        boolean selectWorksheet = false;
//        do {
//            System.out.println("Type \n 1 to view your profile " +
//                    "\n 2 to generate worksheets");
//            int decision = sc.nextInt();
//            if (decision == 1) {
//                System.out.println(userController.getUserDetails(userController.getCurrentUsername()));
//            } else if (decision == 2) {
//                selectWorksheet = true;
//            } else {
//                System.out.println("Invalid Input. Type \n 1 to view your profile " +
//                        "\n 2 to generate worksheets");
//            }
//        } while (!selectWorksheet);
//    }
//
//    public void pressGenerateWorksheet() {
//        //Generate the randomized equation for this worksheet.
//        wGenerator.generateWorksheet(this.equationType, this.numEquations, this.difficulty);
//        //Make the worksheet viewable by adding formatting parameters.
//        pPresenter.createWorksheetPDF(this.worksheetTitle, this.fontSize, this.equationFormat);
//
//        //Display the worksheet in the console (temporary).
//        String[] pdfs = pPresenter.getPDFs();
//        System.out.println("Questions");
//        System.out.println(pdfs[0]);
//        System.out.println("Questions + Answers");
//        System.out.println(pdfs[1]);
//
//        //Store the user's actions into User.
//        HashMap<String, Object> userAction = new HashMap<>();
//        userAction.put("EquationBuilder.Equation type", this.equationType);
//        userAction.put("Number of equations", this.numEquations);
//        userAction.put("Difficulty", this.difficulty);
//        userController.storeUserAction(userController.getCurrentUsername(), userAction);
//
//    }
//
//    public void pressDownloadPDF() {
//        pPresenter.downloadPDF("path/path/path");
//    }
//
//    public void pressUserHistory() {
//        System.out.println(userController.getUserHistory(userController.getCurrentUsername()));
//    }
//
//    public String getUsername() {
//        String username;
//        boolean usernameEntered = false;
//        System.out.println("Enter your username ('main' is the only username that is registered, you can select another if you want):");
//        do {
//            Scanner sc = new Scanner(System.in);
//            username = sc.nextLine();
//
//            if (!userController.verifyUsername(username)) {
//                System.out.println("This username does not exist. Type \n 1 to register a account for this username " +
//                        "\n anything other int to re-enter your username");
//                int registerChoice = sc.nextInt();
//                if (registerChoice == 1) {
//                    registerUsername(username);
//                    usernameEntered = true;
//                }
//            } else {
//                usernameEntered = true;
//            }
//        } while (!usernameEntered);
//        return username;
//    }
//
//    private void registerUsername(String username) {
//        boolean registered = false;
//        Scanner sc = new Scanner(System.in);
//        do {
//            System.out.println("Enter your name");
//            String name = sc.nextLine();
//            System.out.println("Enter your age");
//            int age = sc.nextInt();
//            System.out.println("Enter your role (teacher, student)");
//            sc.nextLine(); // Removes the /n char
//            String role = sc.nextLine();
//            try {
//                userController.createUser(username, name, age, role);
//                registered = true;
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        } while (!registered);
//    }
//
//    public String chooseDifficulty() {
//        String decision;
//        System.out.println("Choose a difficulty level (Easy, Medium, or Hard): ");
//        Scanner sc = new Scanner(System.in);
//        do {
//            decision = sc.nextLine();
//
//            if (!Objects.equals(decision, "Easy") && !Objects.equals(decision, "Medium")
//                    && !Objects.equals(decision, "Hard")) {
//                System.out.println("Invalid Input. Choose a difficulty level (Easy, Medium, or Hard): ");
//            }
//        } while (!Objects.equals(decision, "Easy") && !Objects.equals(decision, "Medium")
//                && !Objects.equals(decision, "Hard"));
//        return decision;
//    }
//
//    public String chooseQuestionFormat() {
//        String decision;
//        System.out.println("Choose the question format (Horizontal or Vertical): ");
//        Scanner sc = new Scanner(System.in);
//        do {
//            decision = sc.nextLine();
//
//            if (!Objects.equals(decision, "Horizontal") && !Objects.equals(decision, "Vertical")) {
//                System.out.println("Invalid Input. Choose the question format (Horizontal or Vertical): ");
//            }
//        } while (!Objects.equals(decision, "Horizontal") && !Objects.equals(decision, "Vertical"));
//        return decision;
//    }
//
//    public void finalPage() {
//        int decision;
//        Scanner sc = new Scanner(System.in);
//
//        //Prompt user to generate and preview the worksheet.
//        do {
//            System.out.println("The worksheet is customized. Type \n 1 to generate and preview the worksheet " +
//                    "\n 2 to exit");
//            decision = sc.nextInt();
//            if (decision == 1) {
//                pressGenerateWorksheet();
//            } else if (decision == 2) {
//                System.exit(0);
//            } else {
//                System.out.println("Invalid Input. Type \n 1 to generate and preview the worksheet " +
//                        "\n 2 to exit");
//            }
//        } while (decision != 1);
//
//        do {
//            //Prompt user to download the worksheet or see their history.
//            System.out.println("Would you like to download the worksheet or see your history? Type \n 1 to download " +
//                    "\n 2 to view history" + "\n 3 to exit the program");
//            decision = sc.nextInt();
//            if (decision == 1) {
//                pressDownloadPDF();
//            } else if (decision == 2) {
//                pressUserHistory();
//            } else if (decision == 3) {
//                System.exit(0);
//            } else {
//                System.out.println("Invalid Input.Type \n 1 to download " +
//                        "\n 2 to view history" + "\n 3 to exit the program");
//            }
//        } while (true);
//
//    }
//
//    public static void main(String[] args) {
//        new UserInterface();
//    }
//}

import user_interface.LoginScreen;
import user_interface.Screen;
import user_package.UserController;
import worksheet_maker.WorksheetController;

/**
 * User Interface class. Creates instances of user controller and worksheet controller
 * to be passed to Screen.
 *
 * @author Ethan Ing
 * @since 2021-11-01
 */
class StartUserInterface {

    static UserController user_controller;
    static WorksheetController worksheet_controller;
    static Screen screen;

    public static void main(String[] args) {

        // Create an instance of user controller to keep track of the user's information
        try {
            user_controller = new UserController();
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Create an instance of worksheet controller to generate the worksheet
        worksheet_controller = new WorksheetController();

        screen.userController = user_controller;
        screen.worksheetController = worksheet_controller;
        screen.main(null);
    }
}