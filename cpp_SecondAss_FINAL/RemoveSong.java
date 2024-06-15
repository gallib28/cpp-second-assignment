import java.util.ArrayList;
import java.util.List;

public class OPStack {
    private static volatile OPStack instance;
    private final List<OP> stack;

    private OPStack() {
        stack = new ArrayList<>();
    }

    public static OPStack getInstance() {
        if (instance == null) {
            synchronized (OPStack.class) {
                if (instance == null) {
                    instance = new OPStack();
                }
            }
        }
        return instance;
    }

    public synchronized FutureImpl push(OP op) {
        FutureImpl future = new FutureImpl();
        op.setFuture(future);
        stack.add(op);
        notifyAll();
        return future;
    }

    public synchronized OP pop() {
        while (stack.isEmpty()) {
            try {
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        return stack.remove(stack.size() - 1);
    }

    public synchronized boolean isEmpty() {
        return stack.isEmpty();
    }
}
 
