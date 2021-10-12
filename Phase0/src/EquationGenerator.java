/**
 * An interface adapter that can generate and retrieve a PDF.
 *
 * @author Sean Jeong
 * @version 1.0
 * @since 2021-10-12
 */
public class EquationGenerator {
    private Equation eq;

    public EquationGenerator(){

    }
    public static String[] createEquation(String equationType, int difficulty){
        if (equationType == "frac add"){
            BedmasEquation e = new BedmasEquation(difficulty);
        }
        e.solve();
        return e.getEquation(); //returns equation as a string;
    }

}
