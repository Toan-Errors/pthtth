public class FileTWrite extends Thread{
    private String name;
    private int n;
    Write wFile;

    public FileTWrite(String name, int n, Write wFile){
        super();
        this.name = name;
        this.n = n;
        this.wFile = wFile;
    }


    public void run() {
        wFile.WriteFile(name, n);
    }
}


class TestFileWrite {
    public static void main(String[] args) {

        Write wFile = new Write();
        new FileTWrite("Bai1.txt", 20, wFile).start();
        new FileTWrite("Bai2.txt", 30, wFile).start();
        new FileTWrite("Bai3.txt", 40, wFile).start();
    }
}

