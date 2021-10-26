package worksheet_maker;

public interface WorksheetOutput {
    //TODO: Change String[][] to PDF

    /**
     * Returns a String representation of a Worksheet.
     *
     * @return equations formatted as [question, answer] in an array
     */
    public String[][] equationsToStringArray();
}
