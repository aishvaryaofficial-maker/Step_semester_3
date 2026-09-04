import java.util.*;

class BmiCalculator {

    static String getBmiStatus(double bmi) {
        if (bmi < 18.5)
            return "Underweight";
        else if (bmi < 25)
            return "Normal";
        else if (bmi < 30)
            return "Overweight";
        else
            return "Obese";
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        double[] height = new double[5];
        double[] weight = new double[5];

        for (int i = 0; i < 5; i++) {
            System.out.print("Enter height in meters: ");
            height[i] = sc.nextDouble();

            System.out.print("Enter weight in kg: ");
            weight[i] = sc.nextDouble();
        }

        System.out.println("\nPerson\tHeight\tWeight\tBMI\tStatus");

        for (int i = 0; i < 5; i++) {
            double bmi = weight[i] / (height[i] * height[i]);

            System.out.printf("%d\t%.2f\t%.2f\t%.2f\t%s%n",
                    i + 1, height[i], weight[i], bmi, getBmiStatus(bmi));
        }
    }
}