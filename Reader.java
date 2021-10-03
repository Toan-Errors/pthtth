import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

class Reader{
    synchronized void Reader(String name){
        try {
            FileReader fReader = new FileReader(name);
            int i;
            try {
                while((i = fReader.read()) != -1){
                    System.out.print((char) i);
                    try {
                        Thread.sleep(400);
                    } catch (InterruptedException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
                fReader.close();
                System.out.println("\nĐọc file " + name + " thành công");
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}