package threads;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.SynchronousQueue;

public class Aula2 implements Aula {
    @Override 
    public void exibir() {
        BlockingQueue<String> queue = new SynchronousQueue<>();

        Thread producer = new Thread(()-> {
            String[] messages = {"Task1", "Task2", "Task3"};
            try {
                for (String msg: messages) {
                    System.out.println("[Producer] Waiting to hand off:");
                    queue.put(msg);
                    System.out.println("[Producer] Handoff: "+msg);
                }
            } catch (InterruptedException e ){
                Thread.currentThread().interrupt();
            }
        });

        Thread consumer = new Thread(() -> {
            try { 
                for(int i =0; i<3; i++) {
                    Thread.sleep(1500);
                    String received = queue.take();
                    System.out.println("[Consumer] Received: "+received);

                }
            } catch(InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });

        producer.start();
        consumer.start();
    }
}
