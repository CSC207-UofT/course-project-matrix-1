public class Worksheet {
    private String[][] equations; // Looks like [["q1", "a1"], ["q2", "a2"]]
    private String[] PDFs; // Looks like ["q1 = ?, q2 = ?, ...", "q1 = a1, q2 = a2, ..."]

    public String[][] getEquations() {
        return equations;
    }

    public void setEquations(String[][] equations) {
        this.equations = equations;
    }

    public void setPDFs(String [] worksheetPdfs) {
        this.PDFs = worksheetPdfs;
    }

    public String [] getPDFs() {
        return this.PDFs;
    }

}
