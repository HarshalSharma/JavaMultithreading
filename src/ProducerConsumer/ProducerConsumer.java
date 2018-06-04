package ProducerConsumer;

import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class ProducerConsumer {

	public static void main(String args[]){
		ProducerConsumer pc = new ProducerConsumer();
		pc.doWork();
	}
	
	public void doWork(){
		Thread producer = new Thread(new Producer());
		producer.start();
		
		Thread consumer1 = new Thread(new Consumer());
		Thread consumer2 = new Thread(new Consumer());
		consumer1.start();
		consumer2.start();
		
		try {
			producer.join();
			consumer1.join();
			consumer2.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	BlockingQueue<Integer> queue = new ArrayBlockingQueue<>(10);
		
	class Producer implements Runnable{

		@Override
		public void run() {
			Random rand = new Random();
			while(true){
				try {
					Thread.sleep(100);
					queue.put(rand.nextInt(100));
					System.out.println("New item produced..");
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}			
		}
		
		
		
	}

	
	class Consumer implements Runnable{

		@Override
		public void run() {
			while(true){
				try {
					Thread.sleep(400);
					Integer value = queue.take();
					System.out.println("Taken :" + value + "; Queue size is " + queue.size());
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}				
			}
		}
		
	}
	
}
