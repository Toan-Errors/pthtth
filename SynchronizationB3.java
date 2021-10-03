public class SynchronizationB3 extends Thread{
    Reader reader;
    Write write;
    String name;
    int n;

    SynchronizationB3(Reader reader,Write write, String name, int n){
        this.reader = reader;
        this.write = write;
        this.name = name;
        this.n = n;
    }

    synchronized void ReaderWrite(){
        System.out.println("Đọc và ghi file " + name);
        write.WriteFile(name, n);
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        System.out.println("Đọc File: " + name);
        reader.Reader(name);
    }

    @Override
    public void run() {
        ReaderWrite();
        super.run();
    }
}

class TestSyn{
    public static void main(String[] args) {
        Reader reader = new Reader();
        Write write = new Write();

        new SynchronizationB3(reader, write, "bai4.txt", 10).start();
    }
}