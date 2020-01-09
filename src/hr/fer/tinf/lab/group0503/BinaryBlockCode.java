package hr.fer.tinf.lab.group0503;

import java.util.ArrayList;
import java.util.Arrays;
import java.lang.Math;
import java.util.List;
import java.util.Scanner;

public class BinaryBlockCode {
    private int[][] gMatrix;
    private int[][] kMatrix;

    /**
     * Constructor with int matrix
     *
     * @param gMatrix int matrix
     */
    public BinaryBlockCode(int[][] gMatrix) {
        int columns = gMatrix[0].length;
        int rows = gMatrix.length;
        this.gMatrix = new int[rows][columns];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                this.gMatrix[i][j] = gMatrix[i][j];
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
    private static int[][] generateKmatrix(int[][] gMatrix) {
        int columns = gMatrix[0].length;
        int rows = gMatrix.length;
        int numberOfRowsInK = (int) Math.pow(2, rows);
        List<int[]> kMatrixList = new ArrayList<>();

        for (int i = 0; i <= numberOfRowsInK - 1; i++) {
            int[] newRow = new int[columns];

            String bin = Integer.toBinaryString(i);
            String zeros = new String("0");
            zeros = zeros.repeat(rows - bin.length());
            bin = zeros + bin;

            for (int j = 0; j < columns; j++) {
                int counter = 0;
                for (int k = 0; k < rows; k++) {
                    int pom = gMatrix[k][j] * Integer.parseInt(String.valueOf(bin.charAt(k)));
                    if (pom == 1) counter++;
                }
                if (counter % 2 == 0) {
                    newRow[j] = 0;
                } else {
                    newRow[j] = 1;
                }
            }

            int kMatrixRows = kMatrixList.size();
            boolean check = false;
            for (int a = 0; a < kMatrixRows; a++) {
                if (Arrays.deepEquals(new int[][]{newRow}, new int[][]{kMatrixList.get(a)})) {
                    check = true;
                }
            }
            if(!check) kMatrixList.add(newRow);
        }

        int[][] kMatrix = new int[kMatrixList.size()][columns];
        for (int l = 0; l < kMatrixList.size(); l++) {
            for (int z = 0; z < kMatrixList.get(l).length; z++) {
                kMatrix[l][z] = kMatrixList.get(l)[z];
            }
        }

        return kMatrix;
    }

    public int[][] getgMatrix() {
        return gMatrix;
    }

    public void setgMatrix(int[][] gMatrix) {
        this.gMatrix = gMatrix;
    }

    public int[][] getkMatrix() {
        return kMatrix;
    }

    public void setkMatrix(int[][] kMatrix) {
        this.kMatrix = kMatrix;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BinaryBlockCode that = (BinaryBlockCode) o;

        if (!Arrays.deepEquals(gMatrix, that.gMatrix)) return false;
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
    public static boolean isLinear(int [][] firstMatrix, int [][] secodMatrix) {
        for(int i =0;i<firstMatrix.length;i++){
            boolean check = false;
            for(int j = 0;j<secodMatrix.length;j++){
                if(Arrays.deepEquals(new int[][]{firstMatrix[i]}, new int[][]{secodMatrix[j]})){
                    check = true;
                    break;
                }
            }
            if(!check) return false;
        }
        return true;
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
    public static double getCodeSpeed(int rows, int columms) {
        //IDK treba li još što ovdje?!?
        return (rows*1.0/columms);
    }


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int m = sc.nextInt();

        int[][] gMatrix = new int[n][m];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                gMatrix[i][j] = sc.nextInt();
            }
        }

        int[][] kMatrix = generateKmatrix(gMatrix);

        for (int i = 0; i < kMatrix.length; i++) {
            StringBuilder builder = new StringBuilder();
            for (int j = 0; j < kMatrix[0].length; j++) {
                builder.append(kMatrix[i][j] + " ");
            }
            System.out.println(builder);
        }

        System.out.println("n od K tablice je: " + kMatrix.length);
        System.out.println("k od K tablice je: " + kMatrix[0].length);

        int[][] newKMatrix = generateKmatrix(kMatrix);

        for (int i = 0; i < newKMatrix.length; i++) {
            StringBuilder builder = new StringBuilder();
            for (int j = 0; j < newKMatrix[0].length; j++) {
                builder.append(newKMatrix[i][j] + " ");
            }
            System.out.println(builder);
        }
        System.out.println(isLinear(kMatrix, newKMatrix));

        System.out.println(getCodeSpeed(gMatrix.length, gMatrix[0].length));
    }
}
