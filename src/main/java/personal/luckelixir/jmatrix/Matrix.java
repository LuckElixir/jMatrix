package personal.luckelixir.jmatrix;

/**
 * A table of numbers supporting basic numerical operations
 * @param <T>
 */
public interface Matrix<T> {


    void addToMatrix(Matrix<T> matrix) throws SizeDifferenceException;
    void addToMatrix(T addend);
    void subtractFromMatrix(Matrix<T> matrix) throws SizeDifferenceException;
    void subtractFromMatrix(T subtractend);
    T get(int row, int column);
    default T get(int[] cursor) {
        return this.get(cursor[0], cursor[1]);
    }
    void put(int row, int column, T value);
    int getRows();
    int getColumns();
    void scale(T scalar);
    Matrix<T> copy() throws SizeDifferenceException;
    T max();
    T min();
    /**
     * Used alongside streams, this method uses the cursor attribute and iterates over the list while pushing values to it
     * Once the cursor is all the way to the end the matrix cannot be pushed anymore
     */
    void push(T val);
    String toString();



}
