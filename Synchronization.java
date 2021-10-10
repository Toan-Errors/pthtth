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


class LopA{
    synchronized void Display(String s){
        System.out.print("< " + s);
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        System.out.println(" >");
    }
}

class LopB extends Thread{
    LopA a;
    String b;

    LopB(LopA a, String b){
        this.a = a;
        this.b = b;
    }

    @Override
    public void run() {
        a.Display(b);
        super.run();
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
        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        LopA a = new LopA();
        new LopB(a, "Hello").start();
        new LopB(a, "Xin chaof").start();
        new LopB(a, "CHao").start();
    }
}
