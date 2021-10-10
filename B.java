public class B extends Thread{
    synchronized public void run(){
        for (int i=0; i<10; i++) {
            System.out.print((char) (i+65) + " ");
            try {
                Thread.sleep(2500);
            }
            catch (InterruptedException e) {
                
            }
        }
    }
}
