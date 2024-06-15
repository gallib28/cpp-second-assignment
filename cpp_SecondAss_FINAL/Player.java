import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Player {
    private static volatile Player instance;
    private final AtomicReference<Node> head;
    private final Lock lock = new ReentrantLock();
    private final Condition notEmpty = lock.newCondition();
    private boolean noMoreAdds = false;

    private Player() {
        head = new AtomicReference<>(null);
    }

    public static Player getInstance() {
        if (instance == null) {
            synchronized (Player.class) {
                if (instance == null) {
                    instance = new Player();
                }
            }
        }
        return instance;
    }

    public void addSong(Song song) {
        lock.lock();
        try {
            Node newNode = new Node(song);
            Node currentHead;
            do {
                currentHead = head.get();
                newNode.next = currentHead;
            } while (!head.compareAndSet(currentHead, newNode));
            notEmpty.signal();
        } 
        finally {
            lock.unlock();
        }
    }

    public void removeSong() throws InterruptedException {
        lock.lock();
        try {
            while (head.get() == null) {
                if (noMoreAdds) {
                    return;
                }
                notEmpty.await();
            }
            Node currentHead;
            Node newHead;
            do {
                currentHead = head.get();
                if (currentHead == null) {
                    return;
                }
                newHead = currentHead.next;
            } while (!head.compareAndSet(currentHead, newHead));
        }
        finally {
            lock.unlock();
        }
    }

    public void stopAdding() {
        lock.lock();
        try {
            noMoreAdds = true;
            notEmpty.signalAll();
        } finally {
            lock.unlock();
        }
    }

    public int size() {
        int count = 0;
        Node current = head.get();
        while (current != null) {
            count++;
            current = current.next;
        }
        return count;
    }

    private static class Node {
        final Song song;
        Node next;

        Node(Song song) {
            this.song = song;
        }
    }
}
