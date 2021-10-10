import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class WorkerThread implements Runnable {
    String msg;

    public WorkerThread(String s){
        this.msg = s;
    }

    public void run(){
        System.out.println(Thread.currentThread().getName() +" (Start) " + msg);
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() +" (End) ");
    }

    public static void main(String[] args) {
        ExecutorService eService = Executors.newFixedThreadPool(5);
        for(int i = 0; i < 10; i++){
            Runnable worker = new WorkerThread("" + i);
            eService.execute(worker);
        }
        eService.shutdown();
        while(!eService.isTerminated()){}

        System.out.println("Finish");
    }


}
