package worksheet_maker;

import equation_entities.Equation;

import java.util.List;

public interface WorksheetOutput {

    String[][] equationsToStringArray();
    int getQuestionNumber();
}
