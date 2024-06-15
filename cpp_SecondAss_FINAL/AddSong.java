public class AddSong implements Runnable {
    private static OPStack opStack = OPStack.getInstance();
    private static Player player = Player.getInstance();

    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            try {
                OP op = opStack.pop();
                if (op.getOp() == 1) {
                    Song song = op.getSong();
                    player.addSong(song);
                    System.out.println(Thread.currentThread().getId());
                    op.getFuture().resolve(1);
                    System.out.println("size = " + player.size());
                } else {
                    opStack.push(op);
                }
            } catch (Exception e) {
                Thread.currentThread().interrupt();
            }
        }
        player.stopAdding();
        System.out.println("AddSong thread interrupted.");
    }
}
