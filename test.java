import java.io.IOException;

public class test {
    public static void main(String[] args) {
        String str = "- 10 20\\n";
        int n = str.indexOf("\\n");
        System.out.println(str.substring(0, n));
        
    }
}