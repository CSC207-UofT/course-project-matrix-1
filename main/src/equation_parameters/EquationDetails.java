package equation_parameters;

import java.io.Serializable;

public abstract class EquationDetails implements Serializable {
    public int getNumOfEquations() {
        return numOfEquations;
    }

    public void setNumOfEquations(int numOfEquations) {
        this.numOfEquations = numOfEquations;
    }

    public char getOperator() {
        return operator;
    }

    public void setOperator(char operator) {
        this.operator = operator;
    }

    public boolean isNegAllowed() {
        return negAllowed;
    }

    public void setNegAllowed(boolean negAllowed) {
        this.negAllowed = negAllowed;
    }

    private int numOfEquations;
    private char operator;
    private boolean negAllowed;

}
