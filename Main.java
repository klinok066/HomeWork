import java.util.Scanner;
public class Main {

    public static void main(String[] args) {
        calc();
    }

     static void calc() {
        Scanner Scan = new Scanner(System.in);
        double result;

        boolean finish = false;

        while (!finish) {
            System.out.println("Введите первое число");
            int num_1 = Scan.nextInt();
            System.out.println("Введите второе число");
            int num_2 = Scan.nextInt();
            System.out.println("Выберите действие: \n" +
                    "сложение(+), вычитание(-), умножение(*), деление(/) или деление по модулю(%)");

            String what = Scan.next();
            switch (what) {
                case "+":
                    result = num_1 + num_2;
                    System.out.println(result);
                    break;
                case "-":
                    result = num_1 - num_2;
                    System.out.println(result);
                    break;
                case "*":
                    result = num_1 * num_2;
                    System.out.println(result);
                    break;
                case "/":
                    result = num_1 / num_2;
                    System.out.println(result);
                    break;
                case "%":
                    result = num_1 % num_2;
                    System.out.println(result);
                    break;
            }
            
            System.out.println("Продолжить?");
            System.out.println("Yes / Stop");
            String str = Scan.next();
            if (str.equals("Stop"))
                finish = true;
        }
    }
}
