package thread;

import java.util.Objects;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class QueueProducer {
    // 队列元素大小
    private static Integer count = 0;
    // 队列初始空间大小
    private static Integer initialSize = 10;
    // 创建一个阻塞队列
    private final BlockingQueue<Integer> blockingQueue = new ArrayBlockingQueue<>(initialSize);

    public static void main(String[] args) {
        QueueProducer queueProducer = new QueueProducer();
        new Thread(queueProducer.new Producer()).start();
        new Thread(queueProducer.new Consumer()).start();
    }

    class Producer implements Runnable {
        @Override
        public void run() {
            for (int i = 0; i < initialSize; i++) { // 循环次数不得超过队列大小，否则提示消息产生太快，丢掉消息
                boolean offer = blockingQueue.offer(i);// 在队列中生产消息，如果队列满了，返回false
                if (offer) {
                    count++; // 维护大小
                    System.out.println(Thread.currentThread().getName()
                            + "生产者，目前生产的消息还有 " + blockingQueue.remainingCapacity() + " 条");
                    System.out.println(Thread.currentThread().getName()
                            + "生产者，目前总共有 " + blockingQueue.remainingCapacity() + " 剩余空间");
                } else {
                    System.err.println("消息产生太快");
                }
            }
        }
    }

    class Consumer implements Runnable {
        @Override
        public void run() {
            while (true) { // 空轮询
                Integer poll = blockingQueue.poll(); // 如果队列为空，返回null
                if (Objects.nonNull(poll)) { // 队列如果有消息，则进行消费
                    count--; // 维护大小
                    System.err.println(Thread.currentThread().getName()
                            + "消费者消费，目前待消费的消息总共有 " + count + " 条");
                    System.err.println(Thread.currentThread().getName()
                            + "消费者消费，目前总共有 " + blockingQueue.remainingCapacity() + " 剩余空间");
                }
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
