import java.lang.Math;

class Diem{
    private int x;
    private int y;

    public int getX() {
        return this.x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return this.y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public Diem(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public String InToaDo() {
        return String.format("Tọa độ: (%d,%d)", x, y);
    }

    public String TinhKhoangCach(){
        return String.format("Khoảng cách đến tọa độ (0,0): %f", Math.sqrt(x*x + y*y));
    }
}