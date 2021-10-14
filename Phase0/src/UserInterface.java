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
    private String questionFormat;
    private String Title;

    public UserInterface() {
        Scanner sc = new Scanner(System.in);

        System.out.println("Welcome to Matrix!");
        System.out.println("Enter your username:");
        this.userName = sc.nextLine();

        System.out.println("Create a new worksheet? Y or N");
        String decision = sc.nextLine();

        switch (decision) {
            case "Y":
                break;
            case "N":
                System.exit(0);
            default:
                System.out.println("please enter again ");

        }

        System.out.println("ye");
    }
        public static void pressGenerateWorksheet() {
            // WorksheetGenerator.generateWorksheet(this.equationType, this.numEquations, this.difficulty);
            // PDFPresenter.createWorksheetPDF(
        }

        public static void pressPreview () {
            // PDFPresenter.getPDF();
        }
        public static void pressDownloadPDF () {
            // PDFPresenter.downloadPDF();
        }

        public static void main (String[]args){
            new UserInterface();
        }
}