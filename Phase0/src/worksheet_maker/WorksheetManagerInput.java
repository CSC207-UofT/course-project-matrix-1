package worksheet_maker;

public interface WorksheetManagerInput {
    public void setWorksheet(int numOfEquations, char operator, int[] operandRange1, int[] operandRange2, boolean negAllowed);
}
