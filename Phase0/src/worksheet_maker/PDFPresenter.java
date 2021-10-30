package worksheet_maker;

import java.util.HashMap;

public class PDFPresenter {
    //Worksheet output rather than Worksheet
    private final WorksheetOutput ws;

    public PDFPresenter(WorksheetOutput ws){
        this.ws = ws;
    }
    //TODO: change this to hashmap input instead of parameter list
    //format details include equation format, title, number of rows, and number of columns
    public String[][] createPDF(HashMap<String, Object> formatDetails) {
        return ws.equationsToStringArray();
    }
}
