// Stack.java
// you can throw exceptions, and add it to the signature of any of the methods
public interface Stack<E> {
    public FutureImpl push(E e) throws InterruptedException;
    public E pop() throws InterruptedException;
    boolean isEmpty();
    public void resolve(E e, int i);
}
 