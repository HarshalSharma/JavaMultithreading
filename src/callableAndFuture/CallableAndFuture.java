package callableAndFuture;

import java.io.IOException;
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/*
 * To get return results from Threads, we can use callable and Future.
 */
public class CallableAndFuture {

	public static void main(String args[]){
		ExecutorService executors = Executors.newCachedThreadPool();
		
		Future<Integer> futureValue = executors.submit(new Callable<Integer>() {

			@Override
			public Integer call() throws Exception {

				Random random = new Random();
				int duration = random.nextInt(4000);
				System.out.println("Starting....");
				
				if(duration>3000){
					throw new IOException("Sleeping for too long...");
				}
				
				try {
					Thread.sleep(duration);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				System.out.println("Finished...");

				return duration;
			}
			
		});
		
		executors.shutdown();
				
		try {
			//Future.get will automatically blocks.
//			executors.awaitTermination(1, TimeUnit.HOURS);
			System.out.println("Recieved Value after future:" + futureValue.get());
		} catch (InterruptedException | ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
