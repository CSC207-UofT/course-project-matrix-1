import java.util.concurrent.ThreadLocalRandom;

/**
 * An interface adapter that can generate and retrieve a PDF.
 *
 * @author Sean Jeong
 * @version 1.0
 * @since 2021-10-12
 */
public class EquationGenerator {
    private static final String ADDITION = "+";

    public EquationGenerator() {

    }

    /**
     * Create a single randomized equation using the given parameters.
     *
     * @param equationType The type of equations in a worksheet.
     * @param difficulty   Difficulty level of the question, either 1, 2, or 3 (Easy, Medium, or Hard respectively).
     */
    public String[] createEquation(String equationType, int difficulty) throws InvalidInputException{
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
        int max;
        int min;
        int firstNum;
        int secondNum;
        Equation randomEqn;
        if (equationType.equals("standard add")) {
            if (difficulty == 1) {
                max = 30;
                min = 0;
            } else if (difficulty == 2) {
                max = 100;
                min = 0;
            } else if (difficulty == 3) {
                max = 100;
                min = -100;
            } else {
                throw new InvalidInputException();
            }
            firstNum = ThreadLocalRandom.current().nextInt(min, max + 1);
            secondNum = ThreadLocalRandom.current().nextInt(min, max + 1);
            randomEqn = new BedmasEquation(firstNum, secondNum, ADDITION);
        } else {
            throw new InvalidInputException();
        }
        randomEqn.solve();
        return randomEqn.getEquation(); //returns equation as a string;

    }


}
