import java.util.ArrayList;
import java.util.List;

// design the class as a *thread-safe singleton*.
// the methods of this object can be synchronized.
public class OPStack implements Stack<OP> {

	// you are allowed to use this data structure.
	// you must not use any built-in thread-safe data structure.
	private List<OP> ops = new ArrayList<OP>();
	
	@Override
	public FutureImpl push(OP e) {
		// TODO Auto-generated method stub
		// each (distinct) OP inserted into the OPStack has a corresponding Future object.
	}

	@Override
	public OP pop() throws InterruptedException {
		// TODO Auto-generated method stub
		// if the OPStack is empty, the calling thread is *blocked*.
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
	}

	@Override
	public void resolve(OP e, int val) {
		// TODO Auto-generated method stub
		// resolve the corresponding OP object with the answer @val
	}
	
	
}
