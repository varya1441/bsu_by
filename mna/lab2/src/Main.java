import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        System.out.println("\nРешение интегрального уравнения методом механических квадратур\n");
        MQ mq = new MQ();
        mq.fillAk();
        System.out.println("Коэффициенты Ak:");
        mq.printX(mq.Ak);
        System.out.println("");
        mq.calculateSolutions();
        System.out.println("");
        mq.calcInPointF();
        mq.calcInPointV();

        System.out.println("\nРешение интегрального уравнения методом последовательных приближений\n");

        SA sa = new SA();
        sa.calcSoutionsF();
        sa.calcSoutionsV();
        System.out.println("Fi для Фредгольма");
        sa.printX(sa.fiF);
        System.out.println("");
        System.out.println("Fi для Вольтера");
        sa.printX(sa.fiV);
        System.out.println("");
        System.out.println("Значение в точке для Фредгольма");
        sa.calcInPointF();
        System.out.println("");
        System.out.println("Значение в точке для Вольтера");
        sa.calcInPointV();
    }
}

class MQ{
    public static double h;
    public static double a;
    public static double b;
    public static int n;
    public static double lambda;
    public static double[] fF;
    public static double[] fV;
    public static double[] xF;
    public static double[] xV;
    public static double[] Ak;
    public static double[][] AF;
    public static double[][] AV;

    public MQ(){
        n = 10;
        a = 0;
        b = 0.5;
        h = (b - a) / n;
        lambda = 0.5;
        Ak = new double[n + 1];
        fF = new double[n + 1];
        fV = new double[n + 1];
        AF = new double[n + 1][n + 1];
        AV = new double[n + 1][n + 1];
        System.out.println("Полученные узлы");
        for (int i = 0; i <= n; i++) {
            System.out.print((a + i * h) + " ");
        }
        System.out.println("");
        System.out.println("");
    }

    public double K(double x, double s) {
        return Math.tanh(Math.sqrt(1+x*x+s*s));
    }

    public double f(double x) {
        return 2*Math.sin(x)+1;
    }
    public void fillAk() {
        for (int i = 0; i < n; i++) {
            Ak[i] = h ;
        }
        Ak[n] = 0;
    }

    public void fillAF() {
        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= n; j++) {
                if (i == j) {
                    AF[i][j] = 1 - lambda * Ak[j] * K(a + i * h, a + j * h);
                } else {
                    AF[i][j] = -lambda * Ak[j] * K(a + i * h, a + j * h);
                }
            }
        }
    }

    public void fillAV() {
        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= i; j++) {
                if (i == j) {
                    AV[i][j] = 1 - lambda * Ak[j] * K(a + i * h, a + j * h);
                } else {
                    AV[i][j] = -lambda * Ak[j] * K(a + i * h, a + j * h);
                }
            }
        }
    }

    public void fillF(){
        for (int i = 0; i <= n; i++) {
            fF[i] = f(a + i * h);
            fV[i] = f(a + i * h);
        }
    }

    public void calculateSolutions() {
        fillAk();
        fillAF();
        fillAV();
        fillF();
        xF = GaussMethod.calculateSolutions(AF, fF, n + 1);
        xV = GaussMethod.calculateSolutions(AV, fV, n + 1);
        System.out.print("x Для уравнения Фредгольма ");
        printX(xF);
        System.out.print("x Для уравнения Вольтера ");
        printX(xV);
    }

    public void printX(double[] x) {
        for (double item : x) {
            System.out.print(item + " ");
        }
        System.out.println("");
    }

    public void printMatrix(double[][] A) {
        for (double[] row : A) {
            for (double item : row) {
                System.out.print(item + " ");
            }
            System.out.println("");
        }
    }

    public void calcInPointF() {
        double point = (a + b) / 2.2;
        System.out.println("Значение Фредгольма");
        double sol = f(point);
        for (int i = 0; i <= n; i++) {
            sol += lambda * xF[i] * K(point, a + i * h) * Ak[i];
        }
        System.out.println("в точке");
        System.out.println(sol);
    }
    public void calcInPointV() {
        double point = (a + b) / 2.2;
        System.out.println("Значение Вольтера");
        double sol = this.lagranzh(point);
        System.out.println("в точке");
        System.out.println(sol);
    }

    public double lagranzh(double t) {
        double buffCh;
        double buffZn;
        double mnL = 0;
        for (int j = 0; j <= n; j++) {
            buffCh = 1;
            buffZn = 1;
            for (int i = 0; i <= n; i++) {
                if (i != j) {
                    buffCh *= (t - (a + i * h));
                    buffZn *= ((a + j * h) - (a + i * h));
                }
            }
            mnL += buffCh / buffZn * xV[j];
        }
        return mnL;
    }

}
class SA{
    public static double h;
    public static double a;
    public static double b;
    public static int n;
    public static double lambda;
    public static double[] fiF;
    public static double[] fiV;
    public static double[] Ak;
    public SA() {
        n = 10;
        a = 0;
        b = 1.0;
        h = (b - a) / n;
        lambda = 0.5;
        fiF = new double[n + 1];
        fiV = new double[n + 1];
        Ak = new double[n + 1];
    }
    public double K(double x, double s) {
        return 1.0/(2 + Math.sin(Math.PI*(x + s)));
    }
    public double f(double x) {
        return 2 - Math.sin(Math.PI*x);
    }
    public double[] fillInAk(double[] aVector) {
        for (int i = 0; i < n; i++) {
            aVector[i] = h ;
        }
        aVector[n] = 0;
        return aVector;
    }
    public void printX(double[] fi) {
        for (double item : fi) {
            System.out.print(item + " ");
        }
    }
    private double IntegralF(int i, double[] fi) {
        double I = 0;
        for (int k = 0; k <= n; k++) {
            I += (K(a + i * h, a + k * h) * fi[k]);
        }
        return I * h;
    }
    private double IntegralV(int i, double[] fi) {
        double I = 0;
        for (int k = 0; k <= n; k++) {
            I += (K(a + i * h, a + k * h) * fi[k]);
        }
        return I * h;
    }
    private double norm(double[] x1, double[] x) {
        double sum = 0;
        for (int i = 0; i < n; i++) {
            sum += Math.abs(x1[i] - x[i]);
        }
        return sum;
    }
    public void calcSoutionsF() {
        double[] fiPrev = new double[n + 1];
        for (int i = 0; i <= n; i++) {
            fiF[i] = f(a + i * h);
        }
        int kol = 0;
        double eps = 0.00001;
        do {
            fiPrev = Arrays.copyOf(fiF, fiF.length);
            for (int i = 0; i <= n; i++) {
                fiF[i] = lambda * IntegralF(i, fiPrev) + f(a + i * h);
            }
            kol++;
            // System.out.println(norm(fiPrev, fi));
        } while (norm(fiPrev, fiF) >= eps);
        System.out.println("kolF=" + kol);
    }
    public void calcSoutionsV() {
        double[] fiPrev = new double[n + 1];
        for (int i = 0; i <= n; i++) {
            fiV[i] = f(a + i * h);
        }
        int kol = 0;
        double eps = 0.00001;
        do {
            fiPrev = Arrays.copyOf(fiV, fiV.length);
            for (int i = 0; i <= n; i++) {
                fiV[i] = lambda * IntegralV(i, fiPrev) + f(a + i * h);
            }
            kol++;
        } while (norm(fiPrev, fiV) >= eps);
        System.out.println("kolV=" + kol);
    }
    public void calcInPointF() {
        double[] aVector = new double[n + 1];
        fillInAk(aVector);
        double point = (a + b) / 2.2;
        double sol = f(point);
        for (int i = 0; i <= n; i++) {
            sol += lambda * fiF[i] * K(point, a + i * h) * aVector[i];
        }
        System.out.println("в точке");
        System.out.println(sol);
    }
    public void calcInPointV() {
        double point = (a + b) / 2.2;
        double sol = this.lagranzh(point);
        System.out.println("в точке");
        System.out.println(sol);
    }
    public double lagranzh(double t) {
        double buffCh;
        double buffZn;
        double mnL = 0;
        for (int j = 0; j <= n; j++) {
            buffCh = 1;
            buffZn = 1;
            for (int i = 0; i <= n; i++) {
                if (i != j) {
                    buffCh *= (t - (a + i * h));
                    buffZn *= ((a + j * h) - (a + i * h));
                }
            }
            mnL += buffCh / buffZn * fiV[j];
        }
        return mnL;
    }
}

class GaussMethod {
    public static double[] calculateSolutions(double[][] matrix, double[] f, int n) {
        double[] x = new double[n];
        double tmp;
        for (int i = 0; i < n; i++) {
            tmp = matrix[i][i];
            f[i] /= tmp;
            for (int j = n - 1; j >= i; j--) {
                matrix[i][j] /= tmp;
            }
            for (int j = i + 1; j < n; j++) {
                tmp = matrix[j][i];
                f[j] -= tmp * f[i];
                for (int k = n - 1; k >= i; k--) {
                    matrix[j][k] -= tmp * matrix[i][k];
                }
            }
        }
        /*обратный ход*/
        x[n - 1] = f[n - 1];
        for (int i = n - 2; i >= 0; i--) {
            x[i] = f[i];
            for (int j = i + 1; j < n; j++) {
                x[i] -= matrix[i][j] * x[j];
            }
        }
        return x;
    }
}
