package callableAndFuture;

import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

public class InterruptingThreadsInPools {

	public static void main(String args[]) throws InterruptedException {

		System.out.println("Starting....");

		ExecutorService executors = Executors.newCachedThreadPool();

		Future<?> foo = executors.submit(new Callable<Void>() {
			@Override
			public Void call() throws Exception {
				Random rand = new Random();
				for (int i = 0; i < 1E8; i++) {
					if (Thread.currentThread().isInterrupted()) {
						System.out.println("I am interuppted!!");
						break;
					}
					Math.sin(rand.nextDouble());
				}
				return null;
			}
		});

		executors.shutdown();
		
		Thread.sleep(500);
		
//		executors.shutdownNow(); //return a list of awaiting threads.
		foo.cancel(true);
		//if cancel is false then it will cancel if not running..
		
		executors.awaitTermination(1, TimeUnit.HOURS);
		
		System.out.println("Finished....");
	}
}
