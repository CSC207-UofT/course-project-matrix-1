package equation_entities;

/**
 * An equation that can be solved and returns its stored equation.
 *
 * @author Sean Jeong
 * @version 1.0
 * @since 2021-10-12
 */
public interface Equation {

    String[] getEquation();

    void solve();

    String questionToString();

    String answerToString();
}
