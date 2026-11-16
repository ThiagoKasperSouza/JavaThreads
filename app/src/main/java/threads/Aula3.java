package threads;

public class Aula3 implements Aula {
    @Override 
    public void exibir() {
        Thread worker = new Thread(()-> {
            for(int i = 1; i <=3; i++) {
                System.out.println("Worker thread running: step "+i);
                try {
                    Thread.sleep(500);
                } catch(InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
            System.out.println("Worker thread finished");
        });

        System.out.println("Starting worker");
        worker.start();

        try {
            // Main thread pause até worker terminar
            worker.join();
        } catch(InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        System.out.println("Main thread resumes");
    }
}
