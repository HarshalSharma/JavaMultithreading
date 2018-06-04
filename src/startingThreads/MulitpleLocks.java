package startingThreads;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MulitpleLocks {

	public static void main(String args[]){
		MulitpleLocks locks = new MulitpleLocks();
		locks.dowork();
	}
	
	public void dowork(){
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
		
		System.out.println("Time taken:" + (end-start));
		System.out.println("L1:"+ list1.size());
		System.out.println("L2:"+ list2.size());

	}
		
	// two different threads cannot access two different methods simuntaneously because of intrinsic lock
	// which is obtained on the class object.
	// it is then we have to use synchronized code blocks inside the methods.
	
	List<Integer> list1 = new ArrayList<>();
	List<Integer> list2 = new ArrayList<>();
	
	Object lock1 = new Object();
	Object lock2 = new Object();
	
	private Random rand = new Random();
	
	public void method1(){
		synchronized (lock1) {
			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			list1.add(rand.nextInt(100));
		}
	}
	
	public void method2(){
		synchronized (lock2) {
			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			list2.add(rand.nextInt(100));
		}
	}
	
	class MyWork implements Runnable{

		@Override
		public void run() {
			for(int i=0;i<100;i++){
				method1();
				method2();
			}
		}
		
	}
	
}
