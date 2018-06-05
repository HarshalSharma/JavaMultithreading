package callableAndFuture;

import java.util.Random;

public class InterruptingThreads {

	public static void main(String args[]) throws InterruptedException{
	
		System.out.println("Starting....");
		
		Thread thread = new Thread(new Runnable() {
			@Override
			public void run() {
				Random rand = new Random();
				//one raised to the power 6, scientific notations.
				for(int i=0;i<1E8;i++){
					if(Thread.currentThread().isInterrupted()){
						System.out.println("I am interuppted!!");
						break;
					}
					Math.sin(rand.nextDouble());
				}
			}
		});
		thread.start();
		
		Thread.sleep(500);
		thread.interrupt();
		
		thread.join();
		System.out.println("Finished....");
	}
	
}
