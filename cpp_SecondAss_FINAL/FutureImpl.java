public class FutureImpl implements Future { 
    private int value;
    private boolean resolved = false;

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

    public synchronized void resolve(int val) {
        value = val;
        resolved = true;
        notifyAll();
    }

    public synchronized boolean isResolved() {
        return resolved;
    }
}
