public class RemoveSong implements Runnable {
    private static OPStack opStack = OPStack.getInstance();
    private static Player player = Player.getInstance();

    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            try {
                OP op = opStack.pop();
                if (op.getOp() == 2) {
                    player.removeSong();
                    System.out.println(Thread.currentThread().getId());
                    op.getFuture().resolve(2);
                    System.out.println("size = " + player.size());
                    if (player.size() == 0 && opStack.isEmpty()) {
                        player.stopAdding();
                        return;
                    }
                } else {
                    opStack.push(op);
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            } catch (Exception e) {
                Thread.currentThread().interrupt();
            }
        }
        System.out.println("RemoveSong thread interrupted.");
    }
}
