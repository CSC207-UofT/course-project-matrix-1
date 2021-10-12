import java.util.concurrent.ThreadLocalRandom;

/**
 * An interface adapter that can generate and retrieve a PDF.
 *
 * @author Sean Jeong
 * @version 1.0
 * @since 2021-10-12
 */
public class EquationGenerator {
    public EquationGenerator(){

    }

    /**
     * Create a single randomized equation using the given parameters.
     * @param equationType The type of equations in a worksheet.
     * @param difficulty Difficulty level of the question, either 1, 2, or 3 (Easy, Medium, or Hard respectively).
     */
    public String[] createEquation(String equationType, int difficulty){
        equationType (bedmas, multiply, fraction, easy);


//        Hashable<String, > equationType =
//        if (bedmas){
//            BedmasEquation e = createBedmasEquation();
//        }else if (lcmgcm){
//            LcmGcmEquation e = createLcmGcmEquation();
//        }
//        if (multiply, fraction, easy){
//
//        }
//

        if (equationType == "standard add"){
            if (difficulty == 1){
                int max = 10;
                int min = 0;
                int firstNum = ThreadLocalRandom.current().nextInt(min, max + 1);
                int secondNum = ThreadLocalRandom.current().nextInt(min, max + 1);
            }
            Equation e = BedmasEquation(firstNum, secondNum, "add");
        }

        e.solve();
        return e.getEquation(); //returns equation as a string;
    }



}
