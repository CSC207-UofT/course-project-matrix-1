/**
 * Stores a list of equations and the pdf representation.
 *
 * @author Will Jeong
 * @version 1.0
 * @since 2021-10-12
 */
public class Worksheet {
    private String[][] equations; // looks like [["q1", "a1"], ["q2", "a2"]]
    private String[] PDFs; // looks like ["q1 = ?, q2 = ?, ...", "q1 = a1, q2 = a2, ..."]

    // GETTER METHODS
    public String[][] getEquations() {
        return equations;
    }

    public String[] getPDFs() {
        return this.PDFs;
    }

    // SETTER METHODS
    public void setEquations(String[][] equations) {
        this.equations = equations;
    }

    public void setPDFs(String[] worksheetPdfs) {
        this.PDFs = worksheetPdfs;
    }

}
