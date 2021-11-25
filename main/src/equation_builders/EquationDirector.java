package equation_builders;

import equation_entities.BedmasEquation;
import equation_parameters.EquationDetails;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public abstract class EquationDirector {

    public abstract void setEquationBuilder(String operator, String operandType);

    public abstract BedmasEquation getEquation();

    public abstract void constructEquation(EquationDetails equationDetails, int seed);

}
