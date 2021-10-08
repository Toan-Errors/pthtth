package Bai4;

public class NguoiSanXuat extends Thread{
    int sanpham;
    Kho k;

    public NguoiSanXuat(int sanpham, Kho k){
        this.k = k;
        this.sanpham = sanpham;
    }

    @Override
    public void run() {
        while(sanpham > 0){
            int n = (int)(Math.random() * 100);
            if(k.NhapKho(n)){
                System.out.println("Nhập " + n + " sản phẩm vào kho thành công");
                k.XuatKho();
            } else {
                System.out.println("Đang chờ nhập vào kho, còn " + sanpham + " sản phẩm đang chờ nhập");
                while(!k.NhapKho(n)){
                }
            }
            sanpham--;
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        super.run();
    }
}
