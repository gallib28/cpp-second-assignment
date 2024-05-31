
// you can throw exceptions, and add it to the signature of any of the methods
public interface Stack<E> {
	public Future push(E e);
	public E pop();
	boolean isEmpty();
	public void resolve(E e, int i);
}
