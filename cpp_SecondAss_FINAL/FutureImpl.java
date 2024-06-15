public class FutureImpl implements Future {
    private int value;
    private boolean resolved = false;

    public FutureImpl() {
        // Default constructor
    }

    // if the answer has been resolved - return the corresponding value;
    // otherwise - the calling thread is *blocked*.
    public synchronized int get() {
        while (!resolved) {
            try {
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        return value;
    }

    // resolve the answer with value @val
    public synchronized void resolve(int val) {
        if (!resolved) {
            value = val;
            resolved = true;
            notifyAll();
        }
    }
}
 