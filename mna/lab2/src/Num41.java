import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Num41 {
    public static void main(String[] args) {
        try {
            FileWriter writer = new FileWriter(new File("out41.txt"));
            double eps = 0.001;
            double I1 = 0, b = 1, a = -1, sum[] = new double[4], help[]=new double[4];
            double h = (b - a) / 3;
            for (int i = 0; i < 4; i++) {
                help[i] = a + h * i;
                sum[i]=1;
            }
            while (Math.abs(I1 - sum[0]) > eps) {
                I1 = sum[2];
                writer.write(Double.toString(I1) + '\n');
                for (int i = 0; i < 4; i++) {
                    sum[i] = (3 * h / 8) * (f(a + h * i, a) * help[0] + 3 * f(a+h*i, a + h) * help[1] + 3 * f(a+h*i, a + 2 * h) * help[2] + f(a+h*i, a + 3 * h) * help[3]);
                }
                for (int i = 0; i < 4; i++) {
                    help[i]=sum[i];
                }
            }
            writer.close();
        }catch (IOException ex){}
    }
    static double f(double x, double s){
        return Math.exp(-x*s)/(Math.pow(Math.pow(x, 2)+Math.pow(s, 2)+7,1/2));
    }
}
