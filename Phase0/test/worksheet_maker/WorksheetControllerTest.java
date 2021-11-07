package worksheet_maker;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.junit.Test;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

public class WorksheetControllerTest {
    @Test
    public void WorksheetControllerVisualTest() throws IOException {
        HashMap<String, Object> myEquationDetails = new HashMap<>();
        myEquationDetails.put("numOfEquations", 10);
        myEquationDetails.put("operator", '/');
        myEquationDetails.put("operandRange1", new int[]{1, 100});
        myEquationDetails.put("operandRange2", new int[]{5, 9});
        myEquationDetails.put("negAllowed", true);
        HashMap<String, Object> myFormatDetails = new HashMap<>();
        myFormatDetails.put("equationFormat", "Horizontal");
        myFormatDetails.put("title", "Test Worksheet");
        myFormatDetails.put("numRows", 20);
        myFormatDetails.put("numColumns", 5);
        WorksheetController wc = new WorksheetController();
        PDDocument[] pdf = wc.generateWorksheetAndPDF(myEquationDetails, myFormatDetails);
        String path = "C:/Users/willj/Downloads/";
        pdf[0].save(path + "/questions.pdf");
        pdf[0].close();
        pdf[1].save(path + "answers.pdf");
        pdf[1].close();
//        Scanner sc = new Scanner(System.in);
//        System.out.print("Enter a path to save the questions and answers: ");
//        String path = sc.nextLine();
//        if (path.equals("")) {
//            path = "C:/Users/willj/Downloads/";
//        }
//        pdf[0].save(path + "/questions.pdf");
//        pdf[0].close();
//        pdf[1].save(path + "answers.pdf");
//        pdf[1].close();
    }


}