package ThreadPools;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CountDownLatches {

	public static void main(String args[]){
		CountDownLatches cdl = new CountDownLatches();
		cdl.doWork();
	}
	
	public void doWork(){
		CountDownLatch countDownLatch = new CountDownLatch(3);
		
		ExecutorService factory = Executors.newFixedThreadPool(2);
		for(int i=0;i<5;i++){
			factory.submit(new Work(countDownLatch));
		}
		factory.shutdown();		
		try {
			countDownLatch.await();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Countdown latch work finished..");
	}
	
	
	class Work implements Runnable{

		@Override
		public void run() {
			System.out.println("Started....");
			
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			latch.countDown();
			System.out.println("Finished...");
		}
		
		public Work(CountDownLatch latch) {
			this.latch = latch;
		}
		
		private CountDownLatch latch;
		
	}
	
}
