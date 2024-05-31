
\\ this object wraps "an answer".
public interface Future {

	// if the answer has been resolved - return the corresponding value;
	// otherwise - the calling thread is *blocked*.
	public int get();

	// resolve the answer with value @val
	public void resolve(int val);
}
