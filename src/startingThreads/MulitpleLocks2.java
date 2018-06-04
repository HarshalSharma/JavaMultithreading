package startingThreads;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class MulitpleLocks2 {

	public static void main(String args[]) {
		MulitpleLocks2 locks = new MulitpleLocks2();
		locks.dowork();
	}

	public void dowork() {
		Thread t1 = new Thread(new MyWork());
		Thread t2 = new Thread(new MyWork());

		long start = System.currentTimeMillis();
		t1.start();
		t2.start();

		try {
			t1.join();
			t2.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		long end = System.currentTimeMillis();

		System.out.println("Time taken:" + (end - start));
		System.out.println("L1:" + list1.size());
		System.out.println("L2:" + list2.size());

	}
	
	//The same can be achieved by using synchronized version of the collections.
	List<Integer> list1 = Collections.synchronizedList(new ArrayList<Integer>());
	List<Integer> list2 = Collections.synchronizedList(new ArrayList<Integer>());

	private Random rand = new Random();

	public void method1() {
		try {
			Thread.sleep(1);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		list1.add(rand.nextInt(100));
	}

	public void method2() {
		try {
			Thread.sleep(1);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		list2.add(rand.nextInt(100));
	}

	class MyWork implements Runnable {

		@Override
		public void run() {
			for (int i = 0; i < 1000; i++) {
				method1();
				method2();
			}
		}

	}

}
