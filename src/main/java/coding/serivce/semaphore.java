package coding.serivce;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

public class semaphore {

    /**
     * @param @param args
     * @return void 返回类型
     * @Description:
     */
    public static void main(String[] args) throws Exception {

//        RateLimiter rateLimiter = RateLimiter.create(100);
//
//        // when
//        long startTime = ZonedDateTime.now().getSecond();
//        RateLimiter limiter = RateLimiter.create(1);
//        for(int i = 1; i < 10; i = i + 2 ) {
//            double waitTime = limiter.acquire(i);
//            System.out.println("cutTime=" + System.currentTimeMillis() + " acq:" + i + " waitTime:" + waitTime);
//        }
//
//        rateLimiter.acquire(100);
//        doSomeLimitedOperation();
//        long elapsedTimeSeconds = ZonedDateTime.now().getSecond() - startTime;
//
//        // then
//        assertThat(elapsedTimeSeconds <= 1);

        // 线程池
        ExecutorService exec = Executors.newFixedThreadPool(3);
        // 只能5个线程同时访问
        final Semaphore semp = new Semaphore(1);


//        AtomicInteger count =new AtomicInteger(0);
        int count = 0;

        Runnable s = () -> {
            try {
                for (int i = 0; i < 10; i++) {
//                    Thread.currentThread().notify();
//                    Thread.currentThread().wait();
                    semp.acquire();
                    System.out.println("A"+i);
                    semp.release();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        };

        new Thread(s).start();
        new Thread(() -> {
            try {
                for (int i = 0; i < 10; i++) {
//                    Thread.currentThread().notify();
//                    Thread.currentThread().wait();
                    semp.acquire();
                    System.out.println("B"+i);
                    semp.release();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
        new Thread(() -> {
            try {
                int i=0;
                while (i!=10){
//                    Thread.currentThread().notify();
//                    Thread.currentThread().wait();

                    semp.acquire();
                    System.out.println("C"+i);
                    semp.release();
                i++;}
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }


}
