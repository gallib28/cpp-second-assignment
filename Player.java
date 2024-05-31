
// the methods of this object *must not* be synchronized.
// do it lock-free...
// this class must be implementes as a *thread-safe singleton*.
public class Player {
	
	public int add(Song song) {
		// code...
	}
	
	public int remove() {
		// code...
	}
	
	// you can assume that no other thread interferes with this method.
	// this method must iterate over all songs and count them on the fly,
	// it must not return the value of some counter.
	public int size() {
		// code...
	}
}
