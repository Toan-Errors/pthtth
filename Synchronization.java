class Table {
    synchronized void printTable(int n) {
        for (int i = 1; i <= 5; i++) {
            System.out.println(n * i);
            try {
                Thread.sleep(400);
            } catch (Exception e) {
                System.out.println(e);
            }
        }
 
    }
}
 

public class Synchronization {
    public static void main(String args[]) {
        Table obj = new Table();// tao mot object
        Thread t1 = new Thread(){
            @Override
            public void run() {
                obj.printTable(10);
                super.run();
            }
        };

        Thread t2 = new Thread(){
            @Override
            public void run() {
                obj.printTable(100);
                super.run();
            }
        };
        
        t1.start();
        t2.start();
    }
}
