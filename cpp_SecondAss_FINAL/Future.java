 public interface Future {
    // if the answer has been resolved - return the corresponding value;
    // otherwise - the calling thread is *blocked*.
    int get();
    
    // resolve the answer with value @val
    void resolve(int val);
}
