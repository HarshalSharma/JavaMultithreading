package locks;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * Semaphores are used to control access to some resource, a one permit semaphore is like a lock.
 *
 */
public class Semaphores {

	public static void main(String args[]){
//		Semaphore sem = new Semaphore(10);
//		sem.release();		
//		System.out.println("Available permits:" + sem.availablePermits());

		ExecutorService executers = Executors.newCachedThreadPool();
		
		for(int i=0;i<200;i++){
			executers.submit(new Runnable() {
				
				@Override
				public void run() {
					Connection.getInstance().connect();
				}
			});
		}
	
		executers.shutdown();
		
		try {
			executers.awaitTermination(1, TimeUnit.HOURS);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	static class Connection{
		
		private static Connection instance = new Connection();
		
		private Semaphore sem = new Semaphore(10,true);
		
		private int connections = 0;
		
		private Connection(){}
		
		public static Connection getInstance(){
			return instance;			
		}
		
		public void connect(){
			try {
				sem.acquire();
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			synchronized (this) {
				this.connections++;
				System.out.println("Current connection number:" + this.connections);
			}
			
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			synchronized (this) {
				this.connections--;
			}
			sem.release();
		}
		
	}
	
}
