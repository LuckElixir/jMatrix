package personal.luckelixir.jmatrix;

import java.util.Arrays;

public class IntMatrix implements Matrix<Integer> {
    private int[][] matrix;
    private int rows;
    private int columns;

    // Generate an empty
    public IntMatrix(int rows, int columns) {
        this.rows = rows;
        this.columns = columns;
        matrix = new int[rows][columns];
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
    public void scale(Integer scalar) {
        for (int i = 0; i < this.rows; i++) {
            for (int j = 0; j < this.columns; j++) {
                this.put(i, j, this.get(i, j) * scalar);
            }
        }
    }

    @Override
    public Matrix<Integer> copy() throws SizeDifferenceException {
        return new IntMatrix(this.matrix);
    }

    @Override
    public Integer max() {
        int max = Integer.MIN_VALUE;
        for (int[] row : matrix) {
            for (int val : row) {
                if (val > max) {
                    max = val;
                }
            }
        }
        return max;
    }

    @Override
    public Integer min() {
        int min = Integer.MAX_VALUE;
        for (int[] row : matrix) {
            for (int val : row) {
                if (val < min) {
                    min = val;
                }
            }
        }
        return min;
    }

    public IntMatrix(int[][] matrix) {
        this.columns = matrix.length;
        this.rows = matrix[0].length;
        this.matrix = matrix;

    }


    @Override
    public void addToMatrix(Matrix<Integer> matrix) throws SizeDifferenceException {
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
    public void addToMatrix(Integer addend) {
        for (int i = 0; i < this.rows; i++) {
            for (int j = 0; j < this.columns; j++) {
                this.put(i, j, this.get(i, j) + addend);
            }
        }

    }

    @Override
    public void subtractfromMatrix(Matrix<Integer> matrix) throws SizeDifferenceException {
        Matrix<Integer> tmpMatrix = matrix.copy();
        tmpMatrix.scale(-1);
        this.addToMatrix(matrix);
    }

    @Override
    public void subtractfromMatrix(Integer subtractend) {
        this.addToMatrix(-(subtractend));
    }

    @Override
    public Integer get(int row, int column) {
        return matrix[row][column];
    }

    @Override
    public void put(int row, int column, Integer value) {
        matrix[row][column] = value;
    }

    public String toString() {
        String[][] stringArray = new String[rows][columns];
        int maxJustification = 0;

        for (int i = 0; i < this.rows; i++) {
            for (int j = 0; j < this.columns; j++) {
                if (Integer.toString(matrix[i][j]).length() > maxJustification) {
                    maxJustification = Integer.toString(matrix[i][j]).length();
                }
            }
        }

        for (int i = 0; i < this.rows; i++) {
            for (int j = 0; j < this.columns; j++) {
                stringArray[i][j] = String.format("%" + maxJustification + "d", matrix[i][j]);
            }
        }

        return Arrays.deepToString(stringArray).replace("],", "]\n").replace(",", "");
    }


}

