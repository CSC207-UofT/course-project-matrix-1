package worksheet_maker;

public interface PDFOutput {
    //TODO: Return a PDF
    public void presentPDF(String title, int numRows, int numColumns, String equationFormat);
}
