package personal.luckelixir.jmatrix;
import java.util.Arrays;

public class LongMatrix implements Matrix<Long> {
    private long[][] matrix;
    private int rows;
    private int columns;

    // Generate an empty
    public LongMatrix(int rows, int columns) {
        this.rows = rows;
        this.columns = columns;
        matrix = new long[rows][columns];
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
    public void scale(Long scalar) {
        for (int i = 0; i < this.rows; i++){
            for (int j = 0; j < this.columns; j++){
                this.put(i, j, this.get(i, j) * scalar);
            }
        }
    }

    @Override
    public Matrix<Long> copy() throws SizeDifferenceException {
        return new LongMatrix(this.matrix);
    }

    @Override
    public Long max() {
        long max = Long.MIN_VALUE;
        for (long[] row : matrix) {
            for (long val : row) {
                if (val > max) {
                    max = val;
                }
            }
        }
        return max;
    }

    @Override
    public Long min() {
        long min = Long.MAX_VALUE;
        for (long[] row : matrix) {
            for (long val : row) {
                if (val < min) {
                    min = val;
                }
            }
        }
        return min;
    }

    public LongMatrix(long[][] matrix) {
        this.columns = matrix.length;
        this.rows = matrix[0].length;
        this.matrix = matrix;

    }


    @Override
    public void addToMatrix(Matrix<Long> matrix) throws SizeDifferenceException {
        if (this.rows != matrix.getRows() || this.columns != matrix.getColumns()){
            throw new SizeDifferenceException(String.format("Sizes of matrices are different: %1$d by %2$d merging into " +
                    "%3$d by %4$d", this.rows, this.columns, matrix.getRows(), matrix.getColumns()));
        }
        for (int i = 0; i < this.rows; i++){
            for (int j = 0; j < this.columns; j++){
                this.put(i, j, matrix.get(i, j) + this.get(i, j));
            }
        }
    }

    @Override
    public void addToMatrix(Long addend) {
        for (int i = 0; i < this.rows; i++){
            for (int j = 0; j < this.columns; j++){
                this.put(i, j, this.get(i, j) + addend);
            }
        }

    }

    @Override
    public void subtractfromMatrix(Matrix<Long> matrix) throws SizeDifferenceException {
        Matrix<Long> tmpMatrix = matrix.copy();
        tmpMatrix.scale(-1L);
        this.addToMatrix(matrix);
    }

    @Override
    public void subtractfromMatrix(Long subtractend) {
        this.addToMatrix(-(subtractend));
    }

    @Override
    public Long get(int row, int column) {
        return matrix[row][column];
    }

    @Override
    public void put(int row, int column, Long value) {
        matrix[row][column] = value;
    }

    public String toString() {
        String[][] stringArray = new String[rows][columns];
        int maxJustification = 0;

        for (int i = 0; i < this.rows; i++){
            for (int j = 0; j < this.columns; j++){
                if (Long.toString(matrix[i][j]).length() > maxJustification) {
                    maxJustification = Long.toString(matrix[i][j]).length();
                }
            }
        }

        for (int i = 0; i < this.rows; i++){
            for (int j = 0; j < this.columns; j++){
                stringArray[i][j] = String.format("%" + maxJustification + "d", matrix[i][j]);
            }
        }

        return Arrays.deepToString(stringArray).replace("],", "]\n").replace(",", "");
    }



}

