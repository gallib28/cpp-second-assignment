import java.util.ArrayList;
import java.util.List;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		OPStack os = OPStack.getInstnace();
		Player player = Player.getInstance();
		
		Thread t1 = new Thread(new AddSong());
		Thread t2 = new Thread(new AddSong());
		Thread t3 = new Thread(new AddSong());
		Thread t4 = new Thread(new RemoveSong());
		Thread t5 = new Thread(new RemoveSong());
		Thread t6 = new Thread(new RemoveSong());
		t1.start();
		t2.start();
		t3.start();
		t4.start();
		t5.start();
		t6.start();
		
		List<FutureImpl> futures = new ArrayList<FutureImpl>();
		
		for (int i = 0; i < args.length; i++) {
			FutureImpl f = os.push(new OP(Integer.parseInt(args[i])));
			futures.add(f);
		}
		
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		t1.interrupt();
		t2.interrupt();
		t3.interrupt();
		t4.interrupt();
		t5.interrupt();
		t6.interrupt();
		
		try {
			t1.join();
			t2.join();
			t3.join();
			t4.join();
			t5.join();
			t6.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		for (int i = 0; i < futures.size(); i++) {
			System.out.println(futures.get(i).get());
		}
		
		System.out.println("size " + player.size());
	}
}
