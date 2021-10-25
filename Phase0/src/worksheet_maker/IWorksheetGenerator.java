package worksheet_maker;

/**
 * An interface for WorksheetGenerator.
 *
 * @author Sean Jeong
 * @version 1.0
 * @since 2021-10-24.
 */
public interface IWorksheetGenerator {
    void createWorksheet(int numOfEquations, char operator, int[] operandRange1, int[] operandRange2, boolean negAllowed);
}
