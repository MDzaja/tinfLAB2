package hr.fer.tinf.lab.group0503;

import java.util.Arrays;

public class BinaryBlockCode {
    private boolean[][] gMatrix;

    /**
     * Constructor with boolean matrix
     *
     * @param gMatrix boolean gMatrix
     */
    public BinaryBlockCode(boolean[][] gMatrix) {
        this.gMatrix = gMatrix;
    }

    /**
     * Constructor with int matrix
     *
     * @param gMatrix int matrix
     */
    public BinaryBlockCode(int[][] gMatrix) {
        int columns = gMatrix[0].length;
        int rows = gMatrix.length;
        this.gMatrix = new boolean[columns][rows];
        for (int i = 0; i < columns; i++) {
            for (int j = 0; j < rows; j++) {
                this.gMatrix[i][j] = gMatrix[i][j] == 1;
            }
        }
    }

    public boolean[][] getgMatrix() {
        return gMatrix;
    }

    public void setgMatrix(boolean[][] gMatrix) {
        this.gMatrix = gMatrix;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BinaryBlockCode that = (BinaryBlockCode) o;

        return Arrays.deepEquals(gMatrix, that.gMatrix);
    }

    @Override
    public int hashCode() {
        return Arrays.deepHashCode(gMatrix);
    }

    /**
     * Get num of columns
     *
     * @return number of columns
     */
    public int getN() {
        return gMatrix[0].length;
    }

    /**
     * Get number of rows
     *
     * @return number of rows
     */
    public int getK() {
        return gMatrix.length;
    }

    /**
     * Check is block code linear
     *
     * @return boolean true if linear else false
     */
    public boolean isLinear() {
        //TODO
        return false; //REMOVE
    }

    /**
     * Check is matrix standard shape
     *
     * @return true if standard else false
     */
    public boolean isMatrixStandard() {
        //TODO
        return false; //REMOVE
    }

    /**
     * Transform matrix to standard shape
     */
    public void transformMatrixToStandard() {
        //TODO
    }

    /**
     * Calculates code speed
     *
     * @return code speed
     */
    public int getCodeSpeed() {
        //IDK treba li još što ovdje?!?
        return getK() / getN();
    }


    public static void main(String[] args) {
        //TODO
    }
}
