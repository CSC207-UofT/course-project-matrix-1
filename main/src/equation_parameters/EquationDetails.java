package equation_parameters;

import java.io.Serializable;

public abstract class EquationDetails implements Serializable {
    private int numOfEquations;
    private String operator;
    private boolean negAllowed;

    public int getNumOfEquations() {
        return numOfEquations;
    }

    public void setNumOfEquations(int numOfEquations) {
        this.numOfEquations = numOfEquations;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public boolean isNegAllowed() {
        return negAllowed;
    }

    public void setNegAllowed(boolean negAllowed) {
        this.negAllowed = negAllowed;
    }

}
