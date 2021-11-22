package equation_builders;

import equation_entities.BedmasEquation;
import equation_parameters.EquationDetails;
import equation_parameters.FractionAddSubEquationDetails;
import equation_parameters.FractionMultiDivEquationDetails;
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
abstract class FractionBuilder{
    protected BedmasEquation bedmasEquation;
    protected Randomizer rand;


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
    protected abstract void buildOperands(EquationDetails fractionEquationDetails, int seed);



    /**
     * Builds the bedmasEquation's answer.
     */
    protected void buildAnswer() {
        bedmasEquation.solve();
    }
}
