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

            int kMatrixRows = kMatrixList.size();
            boolean check = false;
            for (int a = 0; a < kMatrixRows; a++) {
                if (Arrays.deepEquals(new int[][]{newRow}, new int[][]{kMatrixList.get(a)})) {
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

    public int[][] getgMatrix() {
        return gMatrix;
    }

    public int[][] getkMatrix() {
        return kMatrix;
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
        int[][] secondMatrix = generateKMatrix(this.kMatrix);
        for (int[] ints : kMatrix) {
            boolean check = false;
            for (int[] matrix : secondMatrix) {
                if (Arrays.deepEquals(new int[][]{ints}, new int[][]{matrix})) {
                    check = true;
                    break;
                }
            }
            if (! check) return false;
        }
        return true;
    }

    /**
     * Check is matrix standard shape
     *
     * @return true if standard else false
     */
    public boolean isStandardMatrix() {
        for (int i = 0; i < gMatrix[0].length && i < gMatrix.length; i++) {
            for (int j = 0; j < gMatrix.length; j++) {
                if (gMatrix[j][i] == 1 && i != j || gMatrix[j][i] == 0 && i == j)
                    return false;
            }
        }
        return true;
    }

    /**
     * Transform matrix to standard shape
     */
    public boolean transformMatrixToStandard() {
        if (this.isStandardMatrix())
            return true;
        for (int i = 0; i < gMatrix[0].length && i < gMatrix.length; i++) {
            // provjera je li 1 na dijagonali i ako nije trazenje prvog retka koji u tom
            // stupcu ima 1, xor tog retka i promatranog retka kako bi doveli 1 na
            // dijagonalu
            if (gMatrix[i][i] == 0) {
                int rowWithOne = - 1;
                for (int j = 0; j < gMatrix.length; j++) {
                    if (gMatrix[j][i] == 1) {
                        rowWithOne = j;
                        break;
                    }
                }
                if (rowWithOne == - 1) {
                    System.err.println("Matrix can't be transformed!");
                    return false;
                }
                for (int j = 0; j < gMatrix[0].length; j++) {
                    gMatrix[i][j] = gMatrix[i][j] ^ gMatrix[rowWithOne][j];
                }
            }
            // provjera da ni jedan drugi redak nema 1 u stupcu u kojem je promatrani clan
            // dijagonale, ako ima 1, xor tog retka i retka s promatranim clanom dijagonale
            for (int j = 0; j < gMatrix.length; j++) {
                if (gMatrix[j][i] == 1 && j != i) {
                    for (int k = 0; k < gMatrix[0].length; k++) {
                        gMatrix[j][k] = gMatrix[i][k] ^ gMatrix[j][k];
                    }
                }
            }
        }
        return true;
    }

    /**
     * Calculates code speed
     *
     * @return code speed
     */
    public double getCodeSpeed() {
        return 1.0 * getK() / getN();
    }

    /**
     * Encrypts message with block code
     *
     * @param message message to encrypt
     * @return encrypted message
     */
    public  int[] encryptMessageWithCode(int[] message) {
        int[] encryptedMessage = new int[gMatrix[0].length];

        if (this.isStandardMatrix()) {
            System.arraycopy(message, 0, encryptedMessage, 0, message.length);

            for (int i = message.length; i < gMatrix[0].length; i++) {
                int counter = 0;
                for (int j = 0; j < gMatrix.length; j++) {
                    counter += message[j] * gMatrix[j][i];
                }
                encryptedMessage[i] = (counter % 2 == 0) ? 0 : 1;

            }
        } else {
            for (int i = 0; i < gMatrix[0].length; i++) {
                int counter = 0;
                for (int j = 0; j < gMatrix.length; j++) {
                    counter += message[j] * gMatrix[j][i];
                }
                encryptedMessage[i] = (counter % 2 == 0) ? 0 : 1;
            }
        }
        return encryptedMessage;
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
        if (this.isLinear()) {
            stringBuilder.append("Kod je linearan!\n");
        } else {
            stringBuilder.append("Kod nije linearan!\n");
        }
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

        System.out.println(test);
        System.out.println(test2);

        //END TEST

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

    }
}
