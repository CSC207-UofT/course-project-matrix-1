package equation_entities;

import java.util.Map;

/**
 * An equation that can be solved and returns its stored equation.
 *
 * @author Sean Jeong
 * @version 1.0
 * @since 2021-10-12
 */
public interface Equation{
    /**
     * Returns a Hashmap of String representation of an Equation. Key is operator, operand1, operand2, and answer.
     * Values are a String representation of each of those for a given equation.
     *
     * @return Hashmap of String representation of a Worksheet.
     * Ex. {"operator"="\div","operand1"="10", "operand2"="5", "answer"="2" }
     */
    Map<String, String> equationToHashMap();

    /**
     * Uses the question assigned to the equation to determine the answer, and assigns this answer to this equation's
     * instance of answer.
     */
    void solve();

}
