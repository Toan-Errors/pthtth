import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class FileTReader extends Thread{
    String name;
    Reader reader;

    public FileTReader (String name, Reader reader) {
        this.name = name;
        this.reader = reader;
    }

    

    @Override
    public void run() {
        reader.Reader(name);
        super.run();
    }
}

class TestFileReader{
    public static void main(String[] args) {
        Reader reader = new Reader();
        new FileTReader("Bai1.txt", reader).start();
        new FileTReader("Bai2.txt", reader).start();
        new FileTReader("Bai3.txt", reader).start();
    }
}