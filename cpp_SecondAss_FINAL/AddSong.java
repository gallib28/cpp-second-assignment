import java.util.concurrent.atomic.AtomicInteger;

public class AddSong implements Runnable {
    private static OPStack opStack = OPStack.getInstance();
    private static Player player = Player.getInstance();
    private static final AtomicInteger activeThreads = new AtomicInteger(6); // Adjust the number of threads

    public void run() {
        try {
            while (!Thread.currentThread().isInterrupted()) {
                OP op = opStack.pop();
                if (op.getOp() == 1) {
                    Song song = op.getSong();
                    player.addSong(song);
                    System.out.println(Thread.currentThread().getId());
                    op.getFuture().resolve(1);
                } else {
                    opStack.push(op);
                }
            }
        } catch (Exception e) {
            Thread.currentThread().interrupt();
        } finally {
            if (activeThreads.decrementAndGet() == 0) {
                System.out.println("size = " + player.size());
            }
            System.out.println("AddSong thread interrupted.");
        }
    }
}
 
