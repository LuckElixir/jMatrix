package personal.luckelixir.jmatrix;
import java.util.Arrays;

public class DoubleMatrix implements Matrix<Double> {
    private double[][] matrix;
    private int rows;
    private int columns;
    private int[] cursor = {0, 0};

    // Generate an empty
    public DoubleMatrix(int rows, int columns) {
        this.rows = rows;
        this.columns = columns;
        matrix = new double[rows][columns];
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < columns; c++) {
                matrix[r][c] = 0;
            }
        }
    }

    public int getRows() {
        return rows;
    }

    public int getColumns() {
        return columns;
    }


    @Override
    public void scale(Double scalar) {
        for (int i = 0; i < this.rows; i++) {
            for (int j = 0; j < this.columns; j++) {
                this.put(i, j, this.get(i, j) * scalar);
            }
        }
    }

    @Override
    public Matrix<Double> copy() {
        return new DoubleMatrix(this.matrix);
    }

    @Override
    public Double max() {
        double max = Double.MIN_VALUE;
        for (double[] row : matrix) {
            for (double val : row) {
                if (val > max) {
                    max = val;
                }
            }
        }
        return max;
    }

    @Override
    public Double min() {
        double min = Double.MAX_VALUE;
        for (double[] row : matrix) {
            for (double val : row) {
                if (val < min) {
                    min = val;
                }
            }
        }
        return min;
    }

    @Override
    public void push(Double val) {
        if (cursor[0] >= getRows()) {
            throw new IndexOutOfBoundsException("Cursor is in an invalid location");
        }

        if (cursor[1] >= getColumns() && cursor[0] < getRows()) {
            cursor[0]++;
            cursor[1] = 0;
        }

        this.put(cursor[0], cursor[1], val);
        cursor[1]++;
    }

    public DoubleMatrix(double[][] matrix) {
        this.columns = matrix.length;
        this.rows = matrix[0].length;
        this.matrix = matrix;

    }


    @Override
    public void addToMatrix(Matrix<Double> matrix) throws SizeDifferenceException {
        if (this.rows != matrix.getRows() || this.columns != matrix.getColumns()) {
            throw new SizeDifferenceException(String.format("Sizes of matrices are different: %1$d by %2$d merging into " +
                    "%3$d by %4$d", this.rows, this.columns, matrix.getRows(), matrix.getColumns()));
        }
        for (int i = 0; i < this.rows; i++) {
            for (int j = 0; j < this.columns; j++) {
                this.put(i, j, matrix.get(i, j) + this.get(i, j));
            }
        }
    }

    @Override
    public void addToMatrix(Double addend) {
        for (int i = 0; i < this.rows; i++) {
            for (int j = 0; j < this.columns; j++) {
                this.put(i, j, this.get(i, j) + addend);
            }
        }
    }

    @Override
    public void subtractfromMatrix(Matrix<Double> matrix) throws SizeDifferenceException {
        Matrix<Double> tmpMatrix = matrix.copy();
        tmpMatrix.scale(-1d);
        this.addToMatrix(matrix);
    }

    @Override
    public void subtractfromMatrix(Double subtractend) {
        this.addToMatrix(-(subtractend));
    }

    @Override
    public Double get(int row, int column) {
        return matrix[row][column];
    }

    @Override
    public void put(int row, int column, Double value) {
        matrix[row][column] = value;
    }

    public String toString() {
        String[][] stringArray = new String[rows][columns];
        int maxJustification = 0;

        for (int i = 0; i < this.rows; i++) {
            for (int j = 0; j < this.columns; j++) {
                String testVal = String.format("%.4f", matrix[i][j]);
                if (testVal.length() > maxJustification) {
                    maxJustification = testVal.length();
                }
            }
        }
        maxJustification++;
        for (int i = 0; i < this.rows; i++) {
            for (int j = 0; j < this.columns; j++) {
                stringArray[i][j] = String.format("%" + maxJustification + ".4f", matrix[i][j]);
            }
        }

        return Arrays.deepToString(stringArray).replace("],", "]\n").replace(",", "");
    }



}

