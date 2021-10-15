import java.util.Objects;
import java.util.Scanner;

/**
 * User Interface V1
 */
public class UserInterface {

    private final String equationType;
    private final int numEquations;
    private final String difficulty;
    public String userName;
    private final int fontSize;
    private final String worksheetTitle;
    private final String equationFormat;
    private final Worksheet sharedWorksheet = new Worksheet();
    private final WorksheetGenerator wGenerator = new WorksheetGenerator(sharedWorksheet);
    private final PDFPresenter pPresenter = new PDFPresenter(sharedWorksheet);
    private final UserController userController = new UserController();

    public UserInterface() {
        Scanner sc = new Scanner(System.in);

        // Welcome and prompt user for their username
        System.out.println("Welcome to Matrix!");
        this.userName = getUsername();

        System.out.println("Welcome " + this.userName);

        // Ask if the user would like to create a new worksheet
        System.out.println("Create a new Worksheet");

        // Get the topic the user is interested in
        System.out.println("Choose a topic (you can only use 'standard add'): ");
        this.equationType = sc.nextLine();

        // Get the difficult level of the topic
        this.difficulty = chooseDifficulty();

        // Get the number of questions
        System.out.println("Choose the number of questions: ");
        this.numEquations = sc.nextInt();

        Scanner sct = new Scanner(System.in);

        // Let user customize the page details
        System.out.println("Choose the worksheet title: ");
        this.worksheetTitle = sct.nextLine();
        System.out.println("Choose the font size?");
        this.fontSize = sct.nextInt();
        this.equationFormat = chooseQuestionFormat();

        finalPage();

    }

    public void pressGenerateWorksheet() {
        wGenerator.generateWorksheet(this.equationType, this.numEquations, this.difficulty);
        pPresenter.createWorksheetPDF(this.worksheetTitle, this.fontSize, this.equationFormat);
        String[] pdfs = pPresenter.getPDFs();
        System.out.println("Questions");
        System.out.println(pdfs[0]);
        System.out.println("Questions + Answers");
        System.out.println(pdfs[1]);

    }

    public void pressDownloadPDF() {
        pPresenter.downloadPDF("path/path/path");
    }

    public String getUsername() {
        String username;
        System.out.println("Enter your username ('main' is the only username that works):");
        do {
            Scanner sc = new Scanner(System.in);
            username = sc.nextLine();

            if (!userController.verifyUsername(username)) {
                System.out.println("Invalid username. Enter your username ('main' is the only userName that works):");
            }
        } while (!Objects.equals(username, "main"));
        return username;
    }

    public String chooseDifficulty() {
        String decision;
        System.out.println("Choose a difficulty level (Easy, Medium, or Hard): ");
        Scanner sc = new Scanner(System.in);
        do {
            decision = sc.nextLine();

            if (!Objects.equals(decision, "Easy") && !Objects.equals(decision, "Medium")
                    && !Objects.equals(decision, "Hard")) {
                System.out.println("Invalid Input. Choose a difficulty level (Easy, Medium, or Hard): ");
            }
        } while (!Objects.equals(decision, "Easy") && !Objects.equals(decision, "Medium")
                && !Objects.equals(decision, "Hard"));
        return decision;
    }

    public String chooseQuestionFormat() {
        String decision;
        System.out.println("Choose the question format (Horizontal or Vertical): ");
        Scanner sc = new Scanner(System.in);
        do {
            decision = sc.nextLine();

            if (!Objects.equals(decision, "Horizontal") && !Objects.equals(decision, "Vertical")) {
                System.out.println("Invalid Input. Choose the question format (Horizontal or Vertical): ");
            }
        } while (!Objects.equals(decision, "Horizontal") && !Objects.equals(decision, "Vertical"));
        return decision;
    }

    public void finalPage() {
        int decision;
        Scanner sc = new Scanner(System.in);
        do {
            System.out.println("The worksheet is customized. Type \n 1 to generate and preview the worksheet " +
                    "\n 2 to exit");
            decision = sc.nextInt();
            if (decision == 1) {
                pressGenerateWorksheet();
            } else if (decision == 2) {
                System.exit(0);
            } else {
                System.out.println("Invalid Input. Type \n 1 to generate and preview the worksheet " +
                        "\n 2 to exit");
            }
        } while (decision != 1);

        System.out.println("Would you like to download the worksheet? Type \n 1 to download " +
                "\n 2 to cancel");
        decision = sc.nextInt();
        if (decision == 1) {
            pressDownloadPDF();
        } else if (decision == 2) {
            System.exit(0);
        } else {
            System.out.println("Invalid Input.Type \n 1 to download " +
                    "\n 2 to cancel");
        }

    }

    public static void main(String[] args) {
        new UserInterface();
    }
}