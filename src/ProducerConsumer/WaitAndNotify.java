package ProducerConsumer;

import java.util.Scanner;

public class WaitAndNotify {

	public static void main(String args[]){
		WaitAndNotify wn = new WaitAndNotify();
		wn.doTest();
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
		
		public void produce() throws InterruptedException{
			synchronized (this) {
				System.out.println("Producer Thread Running.....");
				wait();
				System.out.println("Producer Resumed...");
			}
		}
		
		public void consumer() throws InterruptedException{
			Scanner scanner = new Scanner(System.in);
			Thread.sleep(2000);

			synchronized (this) {
				System.out.println("Waiting for input:");
				scanner.nextLine();
				System.out.println("consumer releasing control..");
				notify();
				Thread.sleep(3000);
			}
		}
	}
	
}
