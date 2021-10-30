import java.util.Scanner;

public class test {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        String sms = scanner.nextLine();
        String[] str = sms.split("\n");
        for (String string : str) {
            System.out.println(string);
        }
    }
}
