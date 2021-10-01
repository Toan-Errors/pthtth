public class Bai1 {
    public static void main(String[] args) {


        FileTWrite file1 = new FileTWrite("Bai1.txt", 20);
        FileTWrite file2 = new FileTWrite("Bai2.txt", 30);
        FileTWrite file3 = new FileTWrite("Bai3.txt", 40);

        file1.start();
        file2.start();
        file3.start();

    }
}
