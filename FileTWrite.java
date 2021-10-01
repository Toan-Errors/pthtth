
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;   

public class FileTWrite extends Thread{
    private String name;
    private int n;

    FileTWrite(String name, int n){
        super();
        this.name = name;
        this.n = n;
    }

    public void run() {

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
