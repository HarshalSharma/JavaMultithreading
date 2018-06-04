package locks;

import java.util.Scanner;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ReEntrantLocks {
	
class Runners{
		
		private int count = 0;
		
		/*
		 * Reentrant lock and lock many times and then you have to unlock it that much times.
		 */
		private Lock lock = new ReentrantLock();
		
		private Condition condition = lock.newCondition();
		
		private void increment(){
			for(int i=0;i<1000; i++){
				count++;
			}
		}
		
		public void firstThread() throws InterruptedException{
			lock.lock();
			
			System.out.println("Waiting for signal.......");
			condition.await();
			System.out.println("Now awake!");
			
			try{
				increment();				
			}catch (Exception e) {
				e.printStackTrace();
			}finally {
				System.out.println("releasing lock from first thread..");
				lock.unlock();				
			}
		}
		
		public void secondThread() throws InterruptedException{
			//Just to make the first thread lock on first.
			Thread.sleep(1000);
			
			lock.lock();
			
			System.out.println("Press return to signal:");
			new Scanner(System.in).nextLine();
			System.out.println("Signaling....");
			
			condition.signal();
			
			try{
				increment();				
			}catch (Exception e) {
				e.printStackTrace();
			}finally {
				Thread.sleep(1000);
				System.out.println("releasing lock from second thread..");
				lock.unlock();				
			}
		}

		public void finished() throws InterruptedException{
			System.out.println("Count is " + count);
		}
	}
	
	public static void main(String args[]){
		ReEntrantLocks rel = new ReEntrantLocks();
		rel.doTest();
	}
	
	public void doTest(){
		Runners runners = new Runners();
		Thread t1 = new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					runners.firstThread();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		Thread t2 = new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					runners.secondThread();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		
		
		t1.start();
		t2.start();
		
		try {
			t1.join();
			t2.join();
			runners.finished();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
