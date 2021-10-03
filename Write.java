import java.io.FileWriter;
import java.io.IOException;

public class Write{
    synchronized void WriteFile(String name, int n){
        try{
            FileWriter mWriter = new FileWriter(name);
            for(int i = 0; i < n; i++){
                String s = " " + (int)(Math.random()*100);
                mWriter.write(s);
                System.out.println("Dang ghi file " + name);
                try{
                    Thread.sleep(1000);
                } catch(InterruptedException e){

                }
            }
            mWriter.close();
            System.out.println("Ghi file "+ name + "  thanh cong");

        } catch (IOException e){
            System.err.println("Loi");
        }
    }
}