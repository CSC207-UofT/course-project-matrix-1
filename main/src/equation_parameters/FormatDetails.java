package equation_parameters;

import java.io.Serializable;

/**
 * Holds formatting details for the PDF. This includes the equation format, the title of the worksheet, the number of
 * rows, and the number of columns.
 *
 * @author Sean Jeong
 * @version 1.0
 * @since 2021-12-2
 */
public final class FormatDetails implements Serializable {
    private String equationFormat;
    private String title;
    private int numRows;
    private int numColumns;

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

}
