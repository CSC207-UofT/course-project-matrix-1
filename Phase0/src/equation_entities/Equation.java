package equation_entities;

/**
 * An equation that can be solved and returns its stored equation.
 *
 * @author Sean Jeong
 * @version 1.0
 * @since 2021-10-12
 */
public interface Equation {

    /**
     * Returns the equation, as a [question, answer] string representation
     *
     * @return the list representation of the equation
     */
    String[] getEquation();

    /**
     * Uses the question assigned to the equation to determine the answer, and assigns this answer to this equation's
     * instance of answer.
     */
    void solve();

}
