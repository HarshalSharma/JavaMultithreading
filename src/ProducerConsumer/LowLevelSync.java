package ProducerConsumer;

import java.util.LinkedList;
import java.util.Random;

public class LowLevelSync {

	public static void main(String args[]){
		LowLevelSync lls = new LowLevelSync();
		lls.doTest();
	}
	
	public void doTest(){
		final Processor processor = new Processor();
		Thread producer = new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					processor.produce();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		
		Thread consumer = new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					processor.consumer();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});

		producer.start();
		consumer.start();
		
		try {
			consumer.join();
			producer.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	class Processor {
		
		private LinkedList<Integer> list = new LinkedList<>();
		private final int LIMIT = 10;
		private Object lock = new Object();
		
		public void produce() throws InterruptedException{
			int value = 0;
			
			while(true){
				synchronized (lock) {
					while(list.size()==LIMIT){
						lock.wait();
					}
					list.add(value++);		
					lock.notify();
				}
			}
		}
		
		Random rand = new Random();
		public void consumer() throws InterruptedException{
		
			while(true){
				synchronized(lock){
					while(list.size() == 0){
						lock.wait();
					}
					System.out.println("List size:" + list.size());
					int value = list.removeFirst();
					System.out.println("value:" + value);					
					lock.notify();
				}
				
				Thread.sleep(rand.nextInt(100));
			}
		}
		
	}
	
	
}
