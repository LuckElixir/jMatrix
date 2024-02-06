package personal.luckelixir.jmatrix;

import java.util.Iterator;

/**
 * A table of numbers supporting basic numerical operations
 * @param <T>
 */
public interface Matrix<T> {
    public void addToMatrix(Matrix<T> matrix) throws SizeDifferenceException;
    public void addToMatrix(T addend);
    public void subtractfromMatrix(Matrix<T> matrix) throws SizeDifferenceException;
    public void subtractfromMatrix(T subtractend);
    public T get(int row, int column);
    public void put(int row, int column, T value);
    public int getRows();
    public int getColumns();
    public void scale(T scalar);
    public Matrix<T> copy() throws SizeDifferenceException;
    public T max();
    public T min();


}
