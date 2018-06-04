package ThreadPools;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ThreadPools {

	public static void main(String arsg[]){
		ThreadPools tp  = new ThreadPools();
		tp = new ThreadPools();
		tp.doWork();
	}
	
	public void doWork(){
		ExecutorService factory = Executors.newFixedThreadPool(5);
		for(int i=0;i<20;i++){
			factory.submit(new MyWork(i));
		}
		
		factory.shutdown();
		System.out.println("All work submitted..");
		
		try {
			factory.awaitTermination(1, TimeUnit.HOURS);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("All tasks have completed..");
	}
	
	Random rand = new Random();
		
	class MyWork implements Runnable{

		private int id;
		
		public MyWork(int id) {
			this.id = id;
		}
		
		@Override
		public void run() {
			try {
				Thread.sleep(rand.nextInt(5000));
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			System.out.println("Thread number " + id + " has finished it work.");
		}
	}
	
}
