//随手就是一个死锁 (๑ŐдŐ)b
public class TestEles implements Runnable {

    Integer flag = 1;
    private static Object o1 = new Object();
    private static Object o2 = new Object();
    @Override
    public void run() {
        if (flag == 0) {
            synchronized (o1) {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (o2){
                    System.out.println("不是死锁o2");
                }
            }
        }
        if (flag == 1) {
            synchronized (o2){
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (o1){
                    System.out.println("不是死锁o1");
                }
            }
        }
    }


    public static void  main(String args[]) {
        TestEles t1 = new TestEles();
        TestEles t2 = new TestEles();
        t1.flag = 1;
        t2.flag = 0;
        Thread tt1 = new  Thread(t1);
        Thread tt2 = new  Thread(t2);
        tt1.start();
        tt2.start();
    }

}
