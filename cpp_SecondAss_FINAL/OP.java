public class OP {
    private int op;
    private FutureImpl future; // Use FutureImpl directly
    private Song song;

    public OP(int op) {
        this.op = op;
        this.future = new FutureImpl();
        this.song = new Song(); // Initialize with a default Song
    }

    public OP(int op, Song song) {
        this.op = op;
        this.song = song;
        this.future = new FutureImpl();
    }

    public int getOp() {
        return op;
    }

    public FutureImpl getFuture() {
        return future;
    }

    public void setFuture(FutureImpl future) {
        this.future = future;
    }

    public Song getSong() {
        return song;
    }
}
