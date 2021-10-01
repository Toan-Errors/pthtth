public class TinhPhanSo {
    public static void main(String[] args) {
        PhanSo x = new PhanSo(10, 5);

        System.out.println("Phân số là: " + x.InPhanSo());
        System.out.println("Nghịch đảo phân số là: " + x.NghichDaoPhanSo());
        System.out.println("Giá trị thực là: " + x.GiaTriThuc());

        System.out.println("Cộng 2 phân số là: " + x.CongPhanSo(new PhanSo(4, 5)));
        System.out.println("Trừ 2 phân số là: " + x.TruPhanSo(new PhanSo(4, 5)));
        System.out.println("Nhân 2 phân số là: " + x.NhanPhanSo(new PhanSo(4, 5)));
        System.out.println("Chia 2 phân số là: " + x.ChiaPhanSo(new PhanSo(4, 5)));
    }
}
