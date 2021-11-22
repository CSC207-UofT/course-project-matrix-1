package utilities;

import equation_parameters.EquationDetails;
import equation_parameters.FractionAddSubEquationDetails;

import java.util.ArrayList;
import java.util.HashSet;

public class DistributionCalculator {

    private static final ArrayList<Integer> denomDistribution = new ArrayList<>();
    /**
     * Uses the equation details to assign relative probabilities to each number for addition/subtraction so that
     * the distribution of questions is nicer. This results in denomDistribution being populated with 1 or more
     * copies of every possible operand1 denominator. The greater the number of copies, the more likely it will be
     * selected.
     *
     * @param fracEqnDetails the parameters for fraction equation generation.
     */
    public static void assignProbability(EquationDetails fracEqnDetails) {
        FractionAddSubEquationDetails fracAddSubEqnDetails = (FractionAddSubEquationDetails) fracEqnDetails;
        if (fracAddSubEqnDetails.getOperand1DenomRange()[1] > fracAddSubEqnDetails.getMaxOperand2AndAnswerDenom()) {
            //Restrict the range of the denominators to be smaller than the max denominator for the answers.
            fracAddSubEqnDetails.setOperand1DenomRange(new int[]{fracAddSubEqnDetails.getOperand1DenomRange()[0], fracAddSubEqnDetails.getMaxOperand2AndAnswerDenom()});
        }
        //Assign a relative weight to every number from 1 to the denominator range.
        for (int i = fracAddSubEqnDetails.getOperand1DenomRange()[0]; i < fracAddSubEqnDetails.getOperand1DenomRange()[1] + 1; i++) {
            int maxMultiple = fracAddSubEqnDetails.getMaxOperand2AndAnswerDenom() / i;// the maximum number of times i
            // can fit in the max denominator. Ex. if i = 4 and the max is 21, then maxMultiple = 5.
            HashSet<Integer> totalFactors = new HashSet<>(); // stores all the factors for every multiple of i until i
            // is multiplied by its maxMultiple. Does not store duplicates. The greater this number is, the "nicer" it
            // is to have as a denominator
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
    public static ArrayList<Integer> getDenomDistribution(){
        return denomDistribution;
    }
}
