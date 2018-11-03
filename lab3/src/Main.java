import javax.swing.*;
import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) {
        String fileName = "matrix.txt";
        Double[] rez = new Double[0];
        Double matrix[][] = new Double[0][0];
        try {
            matrix = readMatrix(fileName);
            rez = scalerComposition(matrix);
        } catch (IOException e) {
            System.out.print("no such file");
        } catch (NegativeArraySizeException ex) {
            System.out.print("Negative Array");
        } catch (InputMismatchException ex) {
            System.out.println(" Argument x must be a double!");
        } catch (NullPointerException ex) {
            System.out.println("Converting failed: " + ex.getMessage());

        }catch (NoSuchElementException ex){
            System.out.println("no elements");
        }

        for (int i = 0; i < rez.length; i++) {
            System.out.println(rez[i]);
        }
    }

    public static Double[][] readMatrix(String fileName) throws IOException {
        int n = 0;
        Scanner scanner = null;
        Double matrix[][] = new Double[0][0];
        PrintWriter printWriter = null;

        try {
            scanner = new Scanner(new BufferedReader(new FileReader(fileName)));
            printWriter = new PrintWriter(new FileWriter("output.txt"));

            n = scanner.nextInt();
            if (n < 0) {
                throw new MyException(" Your matrix can't be created");
            }
            matrix = new Double[n][n];
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {

                        matrix[i][j] = scanner.nextDouble();
                }
            }
        } catch (MyException ex) {

            System.out.print(ex.getMessage() + " ");


        } finally {
            if (scanner == null)
                scanner.close();
            if (printWriter != null)
                printWriter.close();
        }


        return matrix;

    }

    public static Double[] scalerComposition(Double[][] matrix) {

        Integer[] indexMax = new Integer[matrix.length * 2];
        Integer[] indexMin = new Integer[matrix[0].length * 2];
        int qMax = 0;
        int qMin = 0;

        double maxElem = Double.MIN_VALUE;
        double minElem = Double.MAX_VALUE;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (maxElem < matrix[i][j]) {
                    qMax = 0;
                    maxElem = matrix[i][j];
                    indexMax[qMax] = j;

                } else if (maxElem == matrix[i][j]) {
                    qMax++;
                    indexMax[qMax] = j;
                }
                if (minElem > matrix[i][j]) {
                    qMin = 0;
                    minElem = matrix[i][j];
                    indexMin[qMin] = i;
                } else if (minElem == matrix[i][j]) {
                    qMin++;
                    indexMin[qMin] = i;
                }
            }
        }
        double result = 0;
        Double[] scComp = new Double[(qMin + 1) * (qMax + 1)];
        int qSc = 0;
        for (int i = 0; i <= qMin; i++) {
            for (int j = 0; j <= qMax; j++) {
                result = 0;
                for (int n = 0; n < matrix.length; n++) {
                    result += matrix[indexMin[i]][n] * matrix[n][indexMax[j]];
                }
                scComp[qSc++] = result;
            }
        }
        return scComp;
    }


}
