package worksheet_maker;

import equation_parameters.FormatDetails;
import equation_parameters.FractionAddSubEquationDetails;
import equation_parameters.FractionMultiDivEquationDetails;
import equation_parameters.WholeNumEquationDetails;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.Random;

import static constants.EquationFormats.*;
import static org.junit.Assert.assertEquals;

public class WorksheetControllerTest {
    private final FormatDetails myFormatDetails = new FormatDetails();
    @Before
    public void init() {
        myFormatDetails.setEquationFormat(HORIZONTAL);
        myFormatDetails.setTitle("Test Worksheet");
        myFormatDetails.setNumRows(2);
        myFormatDetails.setNumColumns(2);
    }
    @Test
    public void WholeNumHorizontalVisualTest() throws IOException {
        WholeNumEquationDetails wholeNumEquationDetails = new WholeNumEquationDetails();
        wholeNumEquationDetails.setNumOfEquations(30);
        wholeNumEquationDetails.setOperator("/");
        wholeNumEquationDetails.setOperandRange1(new int[]{50, 100});
        wholeNumEquationDetails.setOperandRange2(new int[]{5, 15});
        wholeNumEquationDetails.setNegAllowed(true);

        WorksheetController wc = new WorksheetController();
        PDDocument[] pdf = wc.generateWorksheetAndPDF(wholeNumEquationDetails, myFormatDetails, new Random().nextInt(100000));
        //String path = "out/production/course-project-matrix-1/user_package/user_package.users_data/";
        //TODO: Change it back - sean
        String path = "C:\\Users\\seand\\OneDrive - University of Toronto\\Documents\\School\\Year Three\\CSC207\\Project\\Temp";
        pdf[0].save(path + "/whole_num_questions.pdf");
        pdf[0].close();
        pdf[1].save(path + "/whole_num_answers.pdf");
        pdf[1].close();
        assertEquals(2, pdf.length);
    }

    @Test
    public void WholeNumVerticalTest() throws IOException {
        myFormatDetails.setEquationFormat(VERTICAL);
        WholeNumEquationDetails wholeNumEquationDetails = new WholeNumEquationDetails();
        wholeNumEquationDetails.setNumOfEquations(30);
        wholeNumEquationDetails.setOperator("/");
        wholeNumEquationDetails.setOperandRange1(new int[]{50, 100});
        wholeNumEquationDetails.setOperandRange2(new int[]{5, 15});
        wholeNumEquationDetails.setNegAllowed(true);

        WorksheetController wc = new WorksheetController();
        PDDocument[] pdf = wc.generateWorksheetAndPDF(wholeNumEquationDetails, myFormatDetails, new Random().nextInt(100000));
        //String path = "out/production/course-project-matrix-1/user_package/user_package.users_data/";
        //TODO: Change it back - sean
        String path = "C:\\Users\\seand\\OneDrive - University of Toronto\\Documents\\School\\Year Three\\CSC207\\Project\\Temp";
        pdf[0].save(path + "/whole_num_questions.pdf");
        pdf[0].close();
        pdf[1].save(path + "/whole_num_answers.pdf");
        pdf[1].close();
        assertEquals(2, pdf.length);
    }

    @Test
    public void WholeNumDivisionBracketTest() throws IOException {
        myFormatDetails.setEquationFormat(DIVISION_BRACKET);
        WholeNumEquationDetails wholeNumEquationDetails = new WholeNumEquationDetails();
        wholeNumEquationDetails.setNumOfEquations(30);
        wholeNumEquationDetails.setOperator("/");
        wholeNumEquationDetails.setOperandRange1(new int[]{50, 100});
        wholeNumEquationDetails.setOperandRange2(new int[]{5, 15});
        wholeNumEquationDetails.setNegAllowed(true);

        WorksheetController wc = new WorksheetController();
        PDDocument[] pdf = wc.generateWorksheetAndPDF(wholeNumEquationDetails, myFormatDetails, new Random().nextInt(100000));
        //String path = "out/production/course-project-matrix-1/user_package/user_package.users_data/";
        //TODO: Change it back - sean
        String path = "C:\\Users\\seand\\OneDrive - University of Toronto\\Documents\\School\\Year Three\\CSC207\\Project\\Temp";
        pdf[0].save(path + "/whole_num_questions.pdf");
        pdf[0].close();
        pdf[1].save(path + "/whole_num_answers.pdf");
        pdf[1].close();
        assertEquals(2, pdf.length);
    }

    @Test
    public void FracAddVisualTest() throws IOException {
        FractionAddSubEquationDetails fractionAddSubEquationDetails = new FractionAddSubEquationDetails();
        fractionAddSubEquationDetails.setNumOfEquations(30);
        fractionAddSubEquationDetails.setOperator("+");
        fractionAddSubEquationDetails.setOperand1DenomRange(new int[] {10,30});
        fractionAddSubEquationDetails.setMaxOperand2AndAnswerDenom(50);
        fractionAddSubEquationDetails.setMaxOperandValue(2);
        fractionAddSubEquationDetails.setNegAllowed(true);

        WorksheetController wc = new WorksheetController();
        PDDocument[] pdf = wc.generateWorksheetAndPDF(fractionAddSubEquationDetails, myFormatDetails, new Random().nextInt(100000));
        //String path = "out/production/course-project-matrix-1/user_package/user_package.users_data/";
        //TODO: Change it back - sean
        String path = "C:\\Users\\seand\\OneDrive - University of Toronto\\Documents\\School\\Year Three\\CSC207\\Project\\Temp";
        pdf[0].save(path + "/frac_add_questions.pdf");
        pdf[0].close();
        pdf[1].save(path + "/frac_add_answers.pdf");
        pdf[1].close();
        assertEquals(2, pdf.length);
    }
    @Test
    public void FracDivVisualTest() throws IOException {
        FractionMultiDivEquationDetails fractionMultiDivEquationDetails = new FractionMultiDivEquationDetails();
        fractionMultiDivEquationDetails.setNumOfEquations(30);
        fractionMultiDivEquationDetails.setOperator("/");
        fractionMultiDivEquationDetails.setAnsDenominatorRange(new int[] {10, 20});
        fractionMultiDivEquationDetails.setComplexity(1);
        fractionMultiDivEquationDetails.setMaxAnsValue(2);
        fractionMultiDivEquationDetails.setNegAllowed(true);

        WorksheetController wc = new WorksheetController();
        PDDocument[] pdf = wc.generateWorksheetAndPDF(fractionMultiDivEquationDetails, myFormatDetails, new Random().nextInt(100000));
        //String path = "out/production/course-project-matrix-1/user_package/user_package.users_data/";
        //TODO: Change it back - sean
        String path = "C:\\Users\\seand\\OneDrive - University of Toronto\\Documents\\School\\Year Three\\CSC207\\Project\\Temp";
        pdf[0].save(path + "/frac_div_questions.pdf");
        pdf[0].close();
        pdf[1].save(path + "/frac_div_answers.pdf");
        pdf[1].close();
        assertEquals(2, pdf.length);
    }

    @Test
    public void FracMultiVisualTest() throws IOException {
        FractionMultiDivEquationDetails fractionMultiDivEquationDetails = new FractionMultiDivEquationDetails();
        fractionMultiDivEquationDetails.setNumOfEquations(30);
        fractionMultiDivEquationDetails.setOperator("*");
        fractionMultiDivEquationDetails.setAnsDenominatorRange(new int[] {10, 20});
        fractionMultiDivEquationDetails.setComplexity(1);
        fractionMultiDivEquationDetails.setMaxAnsValue(2);
        fractionMultiDivEquationDetails.setNegAllowed(true);

        WorksheetController wc = new WorksheetController();
        PDDocument[] pdf = wc.generateWorksheetAndPDF(fractionMultiDivEquationDetails, myFormatDetails, new Random().nextInt(100000));
        //String path = "out/production/course-project-matrix-1/user_package/user_package.users_data/";
        //TODO: Change it back - sean
        String path = "C:\\Users\\seand\\OneDrive - University of Toronto\\Documents\\School\\Year Three\\CSC207\\Project\\Temp";
        pdf[0].save(path + "/frac_multi_questions.pdf");
        pdf[0].close();
        pdf[1].save(path + "/frac_multi_answers.pdf");
        pdf[1].close();
        assertEquals(2, pdf.length);
    }


}