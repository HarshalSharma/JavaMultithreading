package startingThreads;

import java.util.Random;

public class App2 {

	public static void main(String args[]){
		App2 app2 = new App2();
		app2.doWork();
	}
	
	public void doWork(){
		Thread t1 = new Thread(new MyWork("T1"));
		Thread t2 = new Thread(new MyWork("T2"));
		Thread t3 = new Thread(new MyWork("T3"));
		Thread t4 = new Thread(new MyWork("T4"));
		t1.start();
		t2.start();
		t3.start();
		t4.start();
	}

	
	
	class MyWork implements Runnable{

		String name;
		
		public MyWork(String name) {
			this.name = name;
		}
		
		@Override
		public void run() {
			try {
				Random rand = new Random();
				Thread.sleep(rand.nextInt(1000));
				System.out.println(name + " completed its Work.");
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
	}

}
