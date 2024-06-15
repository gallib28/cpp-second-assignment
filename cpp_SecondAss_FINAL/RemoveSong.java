import java.util.concurrent.atomic.AtomicInteger;

public class RemoveSong implements Runnable {
    private static OPStack opStack = OPStack.getInstance();
    private static Player player = Player.getInstance();
    private static final AtomicInteger activeThreads = new AtomicInteger(6); // Adjust the number of threads

    public void run() {
        try {
            while (!Thread.currentThread().isInterrupted()) {
                OP op = opStack.pop();
                if (op.getOp() == 2) {
                    player.removeSong();
                    System.out.println(Thread.currentThread().getId());
                    op.getFuture().resolve(2);
                    if (player.size() == 0 && opStack.isEmpty()) {
                        player.stopAdding();
                        return;
                    }
                } else {
                    opStack.push(op);
                }
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        } catch (Exception e) {
            Thread.currentThread().interrupt();
        } finally {
            if (activeThreads.decrementAndGet() == 0) {
                System.out.println("size = " + player.size());
            }
            System.out.println("RemoveSong thread interrupted.");
        }
    }
}
 
