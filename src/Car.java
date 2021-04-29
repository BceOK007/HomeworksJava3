import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Car implements Runnable {
    private static int CARS_COUNT;
    private Race race;
    private int speed;
    private String name;
    private  CyclicBarrier cyclicBarrier;
    private static boolean isWinnerFound = false;
    private Lock lock = new ReentrantLock();

    public String getName() {
        return name;
    }

    public int getSpeed() {
        return speed;
    }

    public Car(Race race, int speed, CyclicBarrier cyclicBarrier) {
        this.race = race;
        this.speed = speed;
        CARS_COUNT++;
        this.name = "Участник #" + CARS_COUNT;
        this.cyclicBarrier = cyclicBarrier;
    }

    @Override
    public void run() {
        try {
            System.out.println(this.name + " готовится");
            Thread.sleep(500 + (int)(Math.random() * 800));
            System.out.println(this.name + " готов");
            //1. машина готова, уменьшаем кол-во ожидаемых потоков на 1
            cyclicBarrier.await();
            //4. Ждем команды, что остальные машины и основной поток будут готовы
            cyclicBarrier.await();


        for (int i = 0; i < race.getStages().size(); i++) {
            race.getStages().get(i).go(this);
        }

        //Определяем победителя.
        //Если получилось зайти и победитель еще не найден, заходим и объявляем себя победителем
        if (lock.tryLock() && !isWinnerFound) {
            isWinnerFound = true;
            System.out.println(String.format("%s - WIN", name));
            lock.unlock();
        }

        //5. машина закончила гонку
        cyclicBarrier.await();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}