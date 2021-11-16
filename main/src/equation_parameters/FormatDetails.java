package equation_parameters;

public final class FormatDetails {
    public String getEquationFormat() {
        return equationFormat;
    }

    public void setEquationFormat(String equationFormat) {
        this.equationFormat = equationFormat;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getNumRows() {
        return numRows;
    }

    public void setNumRows(int numRows) {
        this.numRows = numRows;
    }

    public int getNumColumns() {
        return numColumns;
    }

    public void setNumColumns(int numColumns) {
        this.numColumns = numColumns;
    }

    private String equationFormat;
    private String title;
    private int numRows;
    private int numColumns;
}
