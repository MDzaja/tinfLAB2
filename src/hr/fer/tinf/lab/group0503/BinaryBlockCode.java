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
     * Generates K matrix
     *
     * @param gMatrix int matrix
     */
    public BinaryBlockCode(int[][] gMatrix) {
        this.gMatrix = gMatrix;
        this.kMatrix = generateKMatrix(this.gMatrix);
    }

    /**
     * Generate kMatrix function
     *
     * @param gMatrix G matrix
     * @return k Matrix
     */
    private static int[][] generateKMatrix(int[][] gMatrix) {
        int columns = gMatrix[0].length;
        int rows = gMatrix.length;
        int numberOfRowsInK = (int) Math.pow(2, rows);
        List<int[]> kMatrixList = new ArrayList<>();
        for (int i = 0; i <= numberOfRowsInK - 1; i++) {
            int[] newRow = new int[columns];

            String bin = Integer.toBinaryString(i);
            String zeros = "0";
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

            boolean check = false;
            for (int[] ints : kMatrixList) {
                if (Arrays.deepEquals(new int[][]{newRow}, new int[][]{ints})) {
                    check = true;
                }
            }
            if (! check) kMatrixList.add(newRow);
        }

        int[][] kMatrix = new int[kMatrixList.size()][columns];
        for (int l = 0; l < kMatrixList.size(); l++) {
            System.arraycopy(kMatrixList.get(l), 0, kMatrix[l], 0, kMatrixList.get(l).length);
        }

        return kMatrix;
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
        //TODO FIX
        /*for(int i =0;i<firstMatrix.length;i++){
            boolean check = false;
            for(int j = 0;j<secodMatrix.length;j++){
                if(Arrays.deepEquals(new int[][]{firstMatrix[i]}, new int[][]{secodMatrix[j]})){
                    check = true;
                    break;
                }
            }
            if(!check) return false;
        }*/
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
    public double getCodeSpeed() {
        return 1.0 * getK() / getN();
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("G Matrix:\n");
        for (int[] matrix : gMatrix) {
            for (int j = 0; j < gMatrix[0].length; j++) {
                stringBuilder.append(matrix[j]).append(" ");
            }
            stringBuilder.append("\n");
        }
        stringBuilder.append("\n");

        stringBuilder.append("K Matrix:\n");
        for (int[] matrix : kMatrix) {
            for (int j = 0; j < kMatrix[0].length; j++) {
                stringBuilder.append(matrix[j]).append(" ");
            }
            stringBuilder.append("\n");
        }
        stringBuilder.append("\n");

        stringBuilder.append("N od K tablice je: ").append(getN()).append("\n");
        stringBuilder.append("K od K tablice je: ").append(getK()).append("\n");

        stringBuilder.append("Brzina koda je: ").append(getCodeSpeed()).append("\n");

        return stringBuilder.toString();
    }

    public static void main(String[] args) {
        //TEST
        int[][] testGMatrix = {
                {1, 0, 0, 0, 0, 0},
                {0, 1, 0, 1, 1, 0},
                {0, 0, 1, 1, 0, 1}
        };

        int[][] testGMatrix2 = {
                {1, 0, 0, 0},
                {0, 1, 0, 0},
                {0, 0, 1, 0},
                {0, 0, 0, 1}
        };
        BinaryBlockCode test = new BinaryBlockCode(testGMatrix);
        BinaryBlockCode test2 = new BinaryBlockCode(testGMatrix2);
        //END TEST
        System.out.println(test);
        System.out.println(test2);

        Scanner sc = new Scanner(System.in);

        System.out.println("Enter n and m:\n");
        int n = sc.nextInt();
        int m = sc.nextInt();

        int[][] gMatrix = new int[n][m];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                gMatrix[i][j] = sc.nextInt();
            }
        }

        BinaryBlockCode code = new BinaryBlockCode(gMatrix);

        System.out.println(code);

        //Čemu ovo?

        /*int[][] newKMatrix = generateKMatrix(kMatrix);

        for (int i = 0; i < newKMatrix.length; i++) {
            StringBuilder builder = new StringBuilder();
            for (int j = 0; j < newKMatrix[0].length; j++) {
                builder.append(newKMatrix[i][j] + " ");
            }
            System.out.println(builder);
        }
        System.out.println(isLinear(kMatrix, newKMatrix));*/

    }
}
