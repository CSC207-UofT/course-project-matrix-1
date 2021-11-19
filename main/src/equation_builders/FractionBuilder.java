package equation_builders;

import equation_entities.BedmasEquation;
import equation_parameters.FractionEquationDetails;
import utilities.FactorFinder;
import utilities.Randomizer;

import java.util.*;

/**
 * An abstract builder for all fraction equation builders.
 *
 * @author Will Jeong
 * @version 1.0
 * @since 2021-10-30
 */
abstract class FractionBuilder {
    protected BedmasEquation bedmasEquation;
    protected Randomizer rand;
    private ArrayList<Integer> denomDistribution = new ArrayList<>();

    public ArrayList<Integer> getDenomDistribution() {
        return denomDistribution;
    }


    protected BedmasEquation getBedmasEquation() {
        return bedmasEquation;
    }

    /**
     * Creates a new instance of the bedmas equation.
     */
    protected void createNewBedmasEquationProduct() {
        bedmasEquation = new BedmasEquation();
        rand = new Randomizer();
    }

    /**
     * Builds the bedmasEquation's operator.
     */
    protected abstract void buildOperator();

    /**
     * Builds the operands (first and second) for the bedmasEquation.
     * RANDOM SEED (for fixing random number generation):
     * First random operation uses the random seed. Succeeding operations increment the random seed by 5.
     *
     * @param fractionEquationDetails the parameters for fraction equation generation.
     * @param seed                    random seed to fix random generation of operands
     */
    protected abstract void buildOperands(FractionEquationDetails fractionEquationDetails, int seed);

    /**
     * Builds the bedmasEquation's answer.
     */
    protected void buildAnswer() {
        bedmasEquation.solve();
    }


    /**
     * Uses the equation details to assign relative probabilities to each number for addition/subtraction so that
     * the distribution of questions is nicer.
     *
     * @param fracEqnDetails the parameters for fraction equation generation.
     */
    public void assignProbability(FractionEquationDetails fracEqnDetails) {
        if (fracEqnDetails.getDenominatorRange()[1] > fracEqnDetails.getMaxDenominator()) {
            fracEqnDetails.setDenominatorRange(new int[]{fracEqnDetails.getDenominatorRange()[0], fracEqnDetails.getMaxDenominator()});
        }
        //Assign a relative weight to every number from 1 to the denominator range.
        for (int i = fracEqnDetails.getDenominatorRange()[0]; i < fracEqnDetails.getDenominatorRange()[1] + 1; i++) {
            int maxMultiple = fracEqnDetails.getMaxDenominator() / i;// the maximum number of times i can fit in the max denominator.
            HashSet<Integer> totalFactors = new HashSet<>();
            for (int j = 1; j < maxMultiple + 1; j++) {
                totalFactors.addAll(FactorFinder.findFactors(i*j));
            }
            int score = totalFactors.size()-1; // The number of factors across all the possible answers represents the
            // number of numbers that could work as the second denominator, and is thus proportional to its score.
            // Subtract 1 to account for how every number has 1 and itself as a factor.
            for (int s = 0; s<score;s++){
                denomDistribution.add(i);
            }
        }
    }

}
