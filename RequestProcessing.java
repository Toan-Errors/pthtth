import java.io.*;
import java.net.Socket;

public class RequestProcessing extends Thread{
    Socket s;

    public RequestProcessing(Socket s){
        this.s = s;
    }

    @Override
    public void run() {
        OutputStream os;
        InputStream is;
        try {
            os = s.getOutputStream();
            is = s.getInputStream();
            int ch = 0;
            while(true){
                ch = is.read();
                if(ch == -1) break;
                System.out.println((char)ch);
                os.write(ch);
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        super.run();
    }
}
