import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите выражение: ");
        String expression = scanner.nextLine();

        //String input = "toDollars(737.6р + toRubles($85.4) - toRubles($85.4))";
        String input = expression;
        input = CurrencyCalculator.replaceCommas(input);

        int count = CurrencyCalculator.countOccurrences(input,'р', '$');
        int notChangedCount = 0;

        while(true){
            input = CurrencyCalculator.evaluateDollars(input);
            input = CurrencyCalculator.evaluateRubles(input);
            input = CurrencyCalculator.processCommandToDollars(input);
            input = CurrencyCalculator.processCommandToRubles(input);
            int previousNumber = count;
            count = CurrencyCalculator.countOccurrences(input,'р', '$');
            if(previousNumber == count){
                notChangedCount++;
                if (notChangedCount == 3){
                    System.out.println("Invalid input");
                    return;
                }
            }else notChangedCount = 0;
            if (previousNumber == 1 && count == 1){
                if(CurrencyCalculator.validateString(input)){
                    break;
                }else{
                    System.out.println("Invalid input");
                    return;
                }
            }
        }
        input = CurrencyCalculator.findAndRoundDouble(input);
        System.out.println("Result: " + input);
    }
}
