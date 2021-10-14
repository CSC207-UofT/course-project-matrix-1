import java.util.Objects;
import java.util.Scanner;

/**
 * User Interface First Version
 */
public class UserInterface {

    private String equationType;
    private int numEquations;
    private String difficulty;
    public String userName;
    private int fontSize;
    private String worksheetTitle;
    private String questionFormat;


    public UserInterface() {
        Scanner sc = new Scanner(System.in);

        // Welcome and prompt user for their username
        System.out.println("Welcome to Matrix!");
        System.out.println("Enter your username:");
        this.userName = sc.nextLine();
        System.out.println("Welcome " + this.userName);

        // Ask if the user would like to create a new worksheet
        System.out.println("Create a new Worksheet");

        // Get the topic the user is interested in
        System.out.println("Choose a topic: ");
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
        this.questionFormat = chooseQuestionFormat();

        finalPage();

        }

        public void pressGenerateWorksheet() {
            // WorksheetGenerator.generateWorksheet(this.equationType, this.numEquations, this.difficulty);
            // PDFPresenter.createWorksheetPDF(this.fontSize, this.questionFormat, this.worksheetTitle);
        }

        public void pressPreview () {
            // PDFPresenter.getPDF();
        }
        public void pressDownloadPDF () {
            // PDFPresenter.downloadPDF();
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
            System.out.println("The worksheet is customized. Type \n 1 to generate worksheet " +
                    "\n 2 to preview worksheet \n 3 to download the worksheet \n 4 to exit");
            int decision;
            do {
                Scanner sc = new Scanner(System.in);
                decision = sc.nextInt();

                if (decision == 1) {
                    pressGenerateWorksheet();
                }
                else if (decision == 2) {
                    pressPreview();
                }
                else if (decision == 3) {
                    pressDownloadPDF();
                }
                else if (decision == 4) {
                    System.exit(0);
                }
                else {
                    System.out.println("Invalid Input. Type \n 1 to generate worksheet " +
                            "\n 2 to preview worksheet \n 3 to download the worksheet \n 4 to exit");
                }
            } while (decision != 1 && decision != 2 && decision != 3);
        }

        public static void main (String[]args){
            new UserInterface();
        }
}