package worksheet_maker;

import org.scilab.forge.jlatexmath.TeXFormula;

/**
 * Converts an Equation as a String into a TexFormula.
 *
 * @author Sean Jeong
 * @version 1.0
 * @since 2021-111-05.
 */
public class EquationStringToLatex {
    public TeXFormula convertEquationStringToLatex(String[] worksheetStringList, String equationType, boolean withAnswer){
        StringBuilder latexString = new StringBuilder();
//        latexString.append("\\[");
        switch (equationType) {
            case "Horizontal":
                for (int i = 0; !worksheetStringList[i].equals("="); i++) {
                    latexString.append(worksheetStringList[i]);
                }
                latexString.append("=");
                if (withAnswer) {
                    latexString.append(worksheetStringList[worksheetStringList.length - 1]);
                }
                break;
            case "Vertical":
                //TODO: raise notImplementedError;
                break;
            case "Division Bracket":
                //TODO: raise notImplementedError;
                break;
        }
//        latexString.append("\\]");
        System.out.println(latexString.toString());
        return new TeXFormula(String.valueOf(latexString));
    }
}
