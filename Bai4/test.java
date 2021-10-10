package Bai4;

public class test {
    public static void main(String[] args) {
        Kho k = new Kho(300);
        NguoiSanXuat sx = new NguoiSanXuat(10, k);
        sx.start();
    }
}

