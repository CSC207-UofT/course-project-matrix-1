package worksheet_maker;

import equation_parameters.FormatDetails;
import equation_parameters.FractionAddSubEquationDetails;
import equation_parameters.FractionMultiDivEquationDetails;
import equation_parameters.WholeNumEquationDetails;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Random;

import static constants.EquationFormats.*;
import static constants.FractionFormats.MIXED;
import static org.junit.Assert.assertEquals;

public class WorksheetControllerTest {
    private final FormatDetails myFormatDetails = new FormatDetails();
    private final String path = "out/production/course-project-matrix-1/worksheet_tests/";

    @Before
    public void init() throws FileNotFoundException {
        // Create temporary folder
        File file = new File(this.path);
        if (!(file.exists() && file.isDirectory())) {
            boolean fileMade = file.mkdir();
            if (!fileMade) {
                throw new FileNotFoundException("testing directory could not be made!");
            }
        }

        myFormatDetails.setEquationFormat(HORIZONTAL);
        myFormatDetails.setTitle("Test Worksheet");
        myFormatDetails.setNumRows(2);
        myFormatDetails.setNumColumns(2);
    }

    @After
    public void tearDown() {
        // Remove temporary folder
        deleteDirectory(new File(this.path));
    }

    @Test
    public void testWholeNumHorizontalVisual() throws IOException {
        WholeNumEquationDetails wholeNumEquationDetails = new WholeNumEquationDetails();
        wholeNumEquationDetails.setNumOfEquations(30);
        wholeNumEquationDetails.setOperator("/");
        wholeNumEquationDetails.setOperandRange1(new int[]{50, 100});
        wholeNumEquationDetails.setOperandRange2(new int[]{5, 15});
        wholeNumEquationDetails.setNegAllowed(true);

        WorksheetController wc = new WorksheetController();
        PDDocument[] pdf = wc.generateWorksheetAndPDF(wholeNumEquationDetails, myFormatDetails, new Random().nextInt(100000));
        pdf[0].save(path + "/whole_num_horizontal_questions.pdf");
        pdf[0].close();
        pdf[1].save(path + "/whole_num_horizontal_answers.pdf");
        pdf[1].close();
        assertEquals(2, pdf.length);
    }

    @Test
    public void testWholeNumVertical() throws IOException {
        myFormatDetails.setEquationFormat(VERTICAL);
        WholeNumEquationDetails wholeNumEquationDetails = new WholeNumEquationDetails();
        wholeNumEquationDetails.setNumOfEquations(30);
        wholeNumEquationDetails.setOperator("/");
        wholeNumEquationDetails.setOperandRange1(new int[]{50, 100});
        wholeNumEquationDetails.setOperandRange2(new int[]{5, 15});
        wholeNumEquationDetails.setNegAllowed(true);

        WorksheetController wc = new WorksheetController();
        PDDocument[] pdf = wc.generateWorksheetAndPDF(wholeNumEquationDetails, myFormatDetails, new Random().nextInt(100000));
        pdf[0].save(path + "/whole_num_vertical_questions.pdf");
        pdf[0].close();
        pdf[1].save(path + "/whole_num_vertical_answers.pdf");
        pdf[1].close();
        assertEquals(2, pdf.length);
    }

    @Test
    public void testWholeNumDivisionBracket() throws IOException {
        myFormatDetails.setEquationFormat(DIVISION_BRACKET);
        WholeNumEquationDetails wholeNumEquationDetails = new WholeNumEquationDetails();
        wholeNumEquationDetails.setNumOfEquations(30);
        wholeNumEquationDetails.setOperator("/");
        wholeNumEquationDetails.setOperandRange1(new int[]{50, 100});
        wholeNumEquationDetails.setOperandRange2(new int[]{5, 15});
        wholeNumEquationDetails.setNegAllowed(true);

        WorksheetController wc = new WorksheetController();
        int num = new Random().nextInt(100000);
        PDDocument[] pdf = wc.generateWorksheetAndPDF(wholeNumEquationDetails, myFormatDetails, num);
        pdf[0].save(path + "/whole_num_divbracket_questions.pdf");
        pdf[0].close();
        pdf[1].save(path + "/whole_num_divbracket_answers.pdf");
        pdf[1].close();
        assertEquals(2, pdf.length);
    }

    @Test
    public void testFracAddVisual() throws IOException {
        FractionAddSubEquationDetails fractionAddSubEquationDetails = new FractionAddSubEquationDetails();
        fractionAddSubEquationDetails.setNumOfEquations(30);
        fractionAddSubEquationDetails.setOperator("+");
        fractionAddSubEquationDetails.setOperand1DenomRange(new int[]{10, 30});
        fractionAddSubEquationDetails.setMaxOperand2AndAnswerDenom(50);
        fractionAddSubEquationDetails.setMaxOperandValue(2);
        fractionAddSubEquationDetails.setNegAllowed(true);
        fractionAddSubEquationDetails.setFractionFormat(MIXED);

        WorksheetController wc = new WorksheetController();
        PDDocument[] pdf = wc.generateWorksheetAndPDF(fractionAddSubEquationDetails, myFormatDetails, 5168);
        pdf[0].save(path + "/frac_add_questions.pdf");
        pdf[0].close();
        pdf[1].save(path + "/frac_add_answers.pdf");
        pdf[1].close();
        assertEquals(2, pdf.length);
    }

    @Test
    public void testFracDivVisual() throws IOException {
        FractionMultiDivEquationDetails fractionMultiDivEquationDetails = new FractionMultiDivEquationDetails();
        fractionMultiDivEquationDetails.setNumOfEquations(30);
        fractionMultiDivEquationDetails.setOperator("/");
        fractionMultiDivEquationDetails.setAnsDenominatorRange(new int[]{10, 20});
        fractionMultiDivEquationDetails.setComplexity(1);
        fractionMultiDivEquationDetails.setMaxAnsValue(2);
        fractionMultiDivEquationDetails.setNegAllowed(true);
        fractionMultiDivEquationDetails.setFractionFormat(MIXED);

        WorksheetController wc = new WorksheetController();
        int num = new Random().nextInt(100000);
        PDDocument[] pdf = wc.generateWorksheetAndPDF(fractionMultiDivEquationDetails, myFormatDetails, num);
        pdf[0].save(path + "/frac_div_questions.pdf");
        pdf[0].close();
        pdf[1].save(path + "/frac_div_answers.pdf");
        pdf[1].close();
        assertEquals(2, pdf.length);
    }

    @Test
    public void testFracMultiVisual() throws IOException {
        FractionMultiDivEquationDetails fractionMultiDivEquationDetails = new FractionMultiDivEquationDetails();
        fractionMultiDivEquationDetails.setNumOfEquations(30);
        fractionMultiDivEquationDetails.setOperator("*");
        fractionMultiDivEquationDetails.setAnsDenominatorRange(new int[]{10, 20});
        fractionMultiDivEquationDetails.setComplexity(1);
        fractionMultiDivEquationDetails.setMaxAnsValue(2);
        fractionMultiDivEquationDetails.setNegAllowed(true);
        fractionMultiDivEquationDetails.setFractionFormat(MIXED);

        WorksheetController wc = new WorksheetController();
        int num = new Random().nextInt(100000);
        PDDocument[] pdf = wc.generateWorksheetAndPDF(fractionMultiDivEquationDetails, myFormatDetails, num);
        pdf[0].save(path + "/frac_multi_questions.pdf");
        pdf[0].close();
        pdf[1].save(path + "/frac_multi_answers.pdf");
        pdf[1].close();
        assertEquals(2, pdf.length);
    }

    @Test
    public void testLCMHorizontalVisual() throws IOException {
        WholeNumEquationDetails wholeNumEquationDetails = new WholeNumEquationDetails();
        wholeNumEquationDetails.setNumOfEquations(30);
        wholeNumEquationDetails.setOperator("LCM");
        wholeNumEquationDetails.setOperandRange1(new int[]{1, 20});
        wholeNumEquationDetails.setOperandRange2(new int[]{1, 20});
        wholeNumEquationDetails.setNegAllowed(false);

        WorksheetController wc = new WorksheetController();
        PDDocument[] pdf = wc.generateWorksheetAndPDF(wholeNumEquationDetails, myFormatDetails, new Random().nextInt(100000));
        pdf[0].save(path + "/whole_num_LCM_questions.pdf");
        pdf[0].close();
        pdf[1].save(path + "/whole_num_LCM_answers.pdf");
        pdf[1].close();
        assertEquals(2, pdf.length);
    }


    /**
     * Recursively delete a directory.
     *
     * @param directory files to delete
     */
    void deleteDirectory(File directory) {
        File[] allContents = directory.listFiles();
        if (allContents != null) {
            for (File file : allContents) {
                deleteDirectory(file);
            }
        }
    }
}