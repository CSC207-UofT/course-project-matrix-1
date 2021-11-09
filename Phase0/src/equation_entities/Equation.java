package equation_entities;

import java.util.List;

/**
 * An equation that can be solved and returns its stored equation.
 *
 * @author Sean Jeong
 * @version 1.0
 * @since 2021-10-12
 */
public interface Equation {

    /**
     * Returns the array representation of the equation.
     *
     * @return the array representation of the equation, where every symbol is a separate item in the array.
     */
    List<String> getEquation();

    /**
     * Uses the question assigned to the equation to determine the answer, and assigns this answer to this equation's
     * instance of answer.
     */
    void solve();

}
