public class Main {

    public static void main(String[] args) {
        double e = 0, x = 0;
        try {
            if (args.length != 2)
                throw new MyException("Wrong number of params");
            x = Double.parseDouble(args[0]);
            e = Double.parseDouble(args[1]);

            if (Math.abs(e) > 1)
                throw new MyException("Bad epsilon value!");
            System.out.println(sum(x, e));

        } catch (MyException ex) {

            System.out.print(ex.getMessage());

        } catch (NumberFormatException ex) {
            System.out.println(ex.getMessage() + " Argument x must be a double!");
        } catch (IllegalArgumentException ex) {
            System.out.println("Converting failed: " + ex.getMessage());
            return;
        }


    }

    private static double sum(double x, double e) {

        double rez = 0, arg = 0;
        double j;
        int k = 1; int fact=1;

        do {
            j = Math.pow(k, (-0.5));
            fact*=(k+1);
            arg = (x + j) / fact;
            rez += arg;
            k++;
        } while (Math.abs(arg) > e);

        System.out.print("k=" + (k - 1) + "  ");

        return rez;

    }
}