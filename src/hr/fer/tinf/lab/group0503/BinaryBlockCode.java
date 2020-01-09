package hr.fer.tinf.lab.group0503;

import java.util.Arrays;
import java.lang.Math;

public class BinaryBlockCode {
    private boolean[][] gMatrix;
    private boolean[][] kMatrix;

    /**
     * Constructor with boolean matrix
     *
     * @param gMatrix boolean gMatrix
     */
    public BinaryBlockCode(boolean[][] gMatrix) {
        this.gMatrix = gMatrix;
        this.kMatrix = generateKmatrix(this.gMatrix);
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
        this.kMatrix = generateKmatrix(this.gMatrix);
    }

    /**
     * Generate kMatrix function
     *
     * @param gMatrix G matrix
     * @return k Matrix
     */
    private static boolean[][] generateKmatrix(boolean[][] gMatrix) {
        int columns = gMatrix[0].length;
        int rows = gMatrix.length;
        boolean[][] kMatrix = new boolean[columns][(int) Math.pow(2, rows)];
        //TODO fix
        /*boolean[] includedRows=new boolean[rows];
        //Arrays.setAll(array, p -> false);
        for(int k=1;k<Math.pow(2,rows);k++){
            int trueCounter=0;
            boolean[] newRow=new boolean[columns];

            for(int i=0;i<columns;i++){
                for(int j=0;j<rows;j++){
                    if(gMatrix[i] && includedRows[j]){
                        trueCounter++;
                    }
                }
                if(trueCounter%2==0)
                    newRow[i]=false;
                else
                    newRow[i]=true;
            }
            if(!kMatrix.contains(newRow))
                kMatrix.add(newRow);

            for(int i=0;i<columns;i++){
                if(k%pow(2,i)==0)
                    includedRows[i]=!includedRows[i];
            }
        }*/
        return kMatrix;
    }

    public boolean[][] getgMatrix() {
        return gMatrix;
    }

    public void setgMatrix(boolean[][] gMatrix) {
        this.gMatrix = gMatrix;
    }

    public boolean[][] getkMatrix() {
        return kMatrix;
    }

    public void setkMatrix(boolean[][] kMatrix) {
        this.kMatrix = kMatrix;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BinaryBlockCode that = (BinaryBlockCode) o;

        if (! Arrays.deepEquals(gMatrix, that.gMatrix)) return false;
        return Arrays.deepEquals(kMatrix, that.kMatrix);
    }

    @Override
    public int hashCode() {
        int result = Arrays.deepHashCode(gMatrix);
        result = 31 * result + Arrays.deepHashCode(kMatrix);
        return result;
    }

    /**
     * Get num of columns
     *
     * @return number of columns
     */
    public int getN() {
        return kMatrix[0].length;
    }

    /**
     * Get number of rows
     *
     * @return number of rows
     */
    public int getK() {
        return kMatrix.length;
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
