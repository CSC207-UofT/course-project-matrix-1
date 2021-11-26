package equation_builders;

import equation_entities.BedmasEquation;
import equation_parameters.EquationDetails;

public abstract class EquationDirector {

    public abstract void setEquationBuilder(String operator);

    public abstract BedmasEquation getEquation();

    public abstract void constructEquation(EquationDetails equationDetails, int seed);

}
