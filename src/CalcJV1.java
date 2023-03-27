import java.util.Scanner;
    class CalcJV1{ //Калькулятор_Java
        private static Object operat;
        public static void main(String[] args) throws Exception {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Введите пример из двух чисел: ");
            String expression = scanner.nextLine().replaceAll("\\s+", "");
            System.out.println(parse(expression));
        }
        public static String parse(String expression) throws Exception {
            int nomer1;
            int nomer2;
            String operat;
            String result;
            boolean isRoman;
            String[] operands = expression.split("[+\\-*/]");
            if (operands.length != 2) throw new Exception("Должно быть только два числа");
            operat = detectOperation(expression);
            if (operat == null) throw new Exception("Такой вид операции не поддерживается");
            if (Roman.isRoman(operands[0]) && Roman.isRoman(operands[1])) {
                //конвертация
                nomer1 = Roman.convertToArabian(operands[0]);
                nomer2 = Roman.convertToArabian(operands[1]);
                isRoman = true;
            }
            //арабские
            else if (!Roman.isRoman(operands[0]) && !Roman.isRoman(operands[1])) {
                nomer1 = Integer.parseInt(operands[0]);
                nomer2 = Integer.parseInt(operands[1]);
                isRoman = false;
            }
            //разные виды
            else {
                throw new Exception("Числа не соответствуют одному типу");
            }
            if (nomer1 > 10 || nomer2 > 10) {
                throw new Exception("Значения должны быть от 1 до 10");
            }
            int arabian = calc(nomer1, nomer2, operat);
            if (isRoman) {
                //римское
                if (arabian <= 0) {
                    throw new Exception("Римские числа должны быть больше нуля");
                }
                //конвертация
                result = Roman.convertToRoman(arabian);
            } else {
                result = String.valueOf(arabian);
            }
            //результат
            return result;
        }

        static String detectOperation(String expression) {
            if (expression.contains("+")) return "+";
            else if (expression.contains("-")) return "-";
            else if (expression.contains("*")) return "*";
            else if (expression.contains("/")) return "/";
            else return null;
        }
        static int calc(int a, int b, String operat) {

            if (operat.equals("+")) return a + b;
            else if (operat.equals("-")) return a - b;
            else if (operat.equals("*")) return a * b;
            else return a / b;
        }
        class Roman {
            static String[] romanArray = new String[]{"0", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X", "XI",
                    "XII", "XIII", "XIV", "XV", "XVI", "XVII", "XVIII", "XIX", "XX", "XXI", "XXII", "XXIII", "XXIV",
                    "XXV", "XXVI", "XXVII", "XXVIII", "XXIX", "XXX", "XXXI", "XXXII", "XXXIII", "XXXIV", "XXXV", "XXXVI",
                    "XXXVII", "XXXVIII", "XXXIX", "XL", "XLI", "XLII", "XLIII", "XLIV", "XLV", "XLVI", "XLVII", "XLVIII",
                    "XLIX", "L", "LI", "LII", "LIII", "LIV", "LV", "LVI", "LVII", "LVIII", "LIX", "LX", "LXI", "LXII",
                    "LXIII", "LXIV", "LXV", "LXVI", "LXVII", "LXVIII", "LXIX", "LXX", "LXXI", "LXXII", "LXXIII", "LXXIV",
                    "LXXV", "LXXVI", "LXXVII", "LXXVIII", "LXXIX", "LXXX", "LXXXI", "LXXXII", "LXXXIII", "LXXXIV", "LXXXV",
                    "LXXXVI", "LXXXVII", "LXXXVIII", "LXXXIX", "XC", "XCI", "XCII", "XCIII", "XCIV", "XCV", "XCVI", "XCVII",
                    "XCVIII", "XCIX", "C"};

            public static boolean isRoman(String val) {
                for (int i = 0; i < romanArray.length; i++) {
                    if (val.equals(romanArray[i])) {
                        return true;
                    }
                }
                return false;
            }
            public static int convertToArabian(String roman) {
                for (int i = 0; i < romanArray.length; i++) {
                    if (roman.equals(romanArray[i])) {
                        return i;
                    }
                }
                return -1;
            }

            public static String convertToRoman(int arabian) {
                return romanArray[arabian];
            }
        }
    }