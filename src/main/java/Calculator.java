import java.util.Scanner;

public class Calculator {
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите выражение из двух чисел (арабских или римских): ");
        String operation = scanner.nextLine();
        System.out.println(calculate(operation));
    }

    public static String calculate(String expression) throws Exception {
        int temp1;
        int temp2;
        String oper;
        String result;
        boolean isRoman;
        String[] value = expression.split("[+\\-*/]");
        if (value.length != 2) throw new Exception("Должно быть только два числа");
        oper = detectOperation(expression);
        if (oper == null) throw new Exception("Не корректная операция");
        if (ConvertNumber.isRoman(value[0]) && ConvertNumber.isRoman(value[1])) {
            temp1 = ConvertNumber.toArabian(value[0]);
            temp2 = ConvertNumber.toArabian(value[1]);
            isRoman = true;
        } else if (!ConvertNumber.isRoman(value[0]) && !ConvertNumber.isRoman(value[1])) {
            temp1 = Integer.parseInt(value[0]);
            temp2 = Integer.parseInt(value[1]);
            isRoman = false;
        } else {
            throw new Exception("Оба числа должны быть в одном формате");
        }
        if (temp1 > 10 || temp2 > 10) {
            throw new Exception("Диопазон чисел от 1 до 10");
        }
        int arabNumber = calc(temp1, temp2, oper);
        if (isRoman) {
            if (arabNumber <= 0) {
                throw new Exception("Число должно быть больше нуля");
            }
            result = ConvertNumber.convertToRoman(arabNumber);
        } else {
            result = String.valueOf(arabNumber);
        }
        return result;
    }

    static String detectOperation(String expression) {
        if (expression.contains("+")) return "+";
        else if (expression.contains("-")) return "-";
        else if (expression.contains("*")) return "*";
        else if (expression.contains("/")) return "/";
        else return null;
    }

    static int calc(int op1Int, int op2Int, String operator) {
        int temp = 0;
        switch (operator) {
            case "+":
                temp = op1Int + op2Int;
                break;
            case "-":
                temp = op1Int - op2Int;
                break;
            case "/":
                temp = op1Int / op2Int;
                break;
            case "*":
                temp = op1Int * op2Int;
                break;
        }
        return temp;
    }

}

