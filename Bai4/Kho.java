package Bai4;


public class Kho {
    int n;
    int soluong;
    int tongsoluong = 0;

    public Kho(int n){
        this.n = n;
    }

    synchronized public boolean NhapKho(int soluong){
        if(soluong > 0){
            if(soluong <= n){
                this.soluong = soluong;
                tongsoluong += soluong;
                n -= soluong;
                return true;
            }  else {
                return false;
            }
        }
        return false;
    }

    synchronized public boolean XuatKho(){
        if(soluong > 0){
            System.out.println("Số lượng hàng trong kho :" + tongsoluong);
            return true;
        }
        return false;
    }
}
