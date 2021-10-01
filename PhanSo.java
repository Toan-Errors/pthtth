public class PhanSo {
    private int tuso;
    private int mauso;

    public int getTuso() {
        return this.tuso;
    }

    public void setTuso(int tuso) {
        this.tuso = tuso;
    }

    public int getMauso() {
        return this.mauso;
    }

    public void setMauso(int mauso) {
        this.mauso = mauso;
    }

    public PhanSo(int tuso, int mauso){
        super();
        this.tuso = tuso;
        this.mauso = mauso;
    }

    public String InPhanSo(){
        return String.format("%d/%d", tuso, mauso);
    }

    public String NghichDaoPhanSo(){
        return String.format("%d/%d", mauso, tuso);
    }

    public double GiaTriThuc(){
        return tuso/mauso;
    }

    public String CongPhanSo(PhanSo ps){
        int ts = tuso * ps.mauso + ps.tuso * mauso;
        int ms = mauso * ps.mauso;

        return String.format("%d/%d", ts, ms);
    }

    public String TruPhanSo(PhanSo ps){
        int ts = tuso * ps.mauso - ps.tuso * mauso;
        int ms = mauso * ps.mauso;

        return String.format("%d/%d", ts, ms);
    }
    
    public String NhanPhanSo(PhanSo ps){
        int ts = tuso * ps.tuso;
        int ms = mauso * ps.mauso;

        return String.format("%d/%d", ts, ms);
    }

    public String ChiaPhanSo(PhanSo ps){
        int ts = tuso * ps.mauso;
        int ms = mauso * ps.tuso;

        return String.format("%d/%d", ts, ms);
    }
    
}
