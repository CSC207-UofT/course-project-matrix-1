public class Worksheet {
    private String[][] equations; // Looks like [["q1", "a1"], ["q2", "a2"]]
    private String[] pdfs; // Looks like ["q1 = ?, q2 = ?, ...", "q1 = a1, q2 = a2, ..."]

    public String[][] getEquations() {
        return equations;
    }

    public void setEquations(String[][] equations) {
        this.equations = equations;
    }

    public void setPdfs(String [] worksheetPdfs) {
        this.pdfs = worksheetPdfs;
    }

    public String [] getPdfs() {
        return this.pdfs;
    }

}
