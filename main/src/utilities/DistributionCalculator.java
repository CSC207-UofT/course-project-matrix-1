package utilities;

import equation_parameters.EquationDetails;
import equation_parameters.FractionAddSubEquationDetails;

import java.util.ArrayList;

public class DistributionCalculator {

    private static ArrayList<Integer> denomDistribution = new ArrayList<>();

    /**
     * Uses the equation details to assign relative probabilities to each number for addition/subtraction so that
     * the distribution of questions is nicer. This results in denomDistribution being populated with 1 or more
     * copies of every possible operand1 denominator. The greater the number of copies, the more likely it will be
     * selected.
     *
     * @param fracEqnDetails the parameters for fraction equation generation.
     */
    public static void assignProbability(EquationDetails fracEqnDetails) {
        denomDistribution = new ArrayList<>();
        FractionAddSubEquationDetails fracAddSubEqnDetails = (FractionAddSubEquationDetails) fracEqnDetails;
        if (fracAddSubEqnDetails.getOperand1DenomRange()[1] > fracAddSubEqnDetails.getMaxOperand2AndAnswerDenom()) {
            //Restrict the range of the denominators to be smaller than the max denominator for the answers.
            fracAddSubEqnDetails.setOperand1DenomRange(new int[]{fracAddSubEqnDetails.getOperand1DenomRange()[0], fracAddSubEqnDetails.getMaxOperand2AndAnswerDenom()});
        }
        //Assign a relative weight to every number from 1 to the denominator range.
        for (int i = fracAddSubEqnDetails.getOperand1DenomRange()[0]; i < fracAddSubEqnDetails.getOperand1DenomRange()[1] + 1; i++) {
            int score = FactorFinder.findFactors(i).size() - 1; // the greater the number of factors this has, the nicer
            // it is as a denominator. Subtract 1 to account for how every number has itself and 1 as a factor.
            if (i == 1) {
                score++; // if it's 1, then add an extra point since otherwise it would be impossible to get 1.
            }
            for (int s = 0; s < score; s++) {
                denomDistribution.add(i);
            }
        }
    }

    /**
     * Returns the denomDistribution.
     *
     * @return the denomDistribution, which is the probability associated with each denominator.
     */
    public static ArrayList<Integer> getDenomDistribution() {
        return denomDistribution;
    }

    /**
     * Adds an extra copy of the number for every extra factor the number in the list contains, weighing more easily
     * divisible numbers highly.
     *
     * @param unweightedList An unweighted list with only one copy of each number.
     */
    public static void modifyWeights(ArrayList<Integer> unweightedList) {
        int originalLength = unweightedList.size();
        for (int i = 0; i < originalLength; i++) {
            int score = FactorFinder.findFactors(unweightedList.get(i)).size() - 1;
            if (unweightedList.get(i) == 1) {
                score++;
            }
            for (int j = 0; j < score - 1; j++) {
                unweightedList.add(unweightedList.get(i));
            }
        }
    }
}
