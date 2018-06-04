package startingThreads;

public class SynchronizedKeyword {

	public int count = 0;
	
	public synchronized void increment(){
		count++;
	}
	
	public static void main(String args[]){
		SynchronizedKeyword keyword = new SynchronizedKeyword();
		keyword.dowork();
	}
	
	public void dowork(){
		Thread t1 = new Thread(new MyWork());
		Thread t2 = new Thread(new MyWork());
		t1.start();
		t2.start();
		
		try {
			t1.join();
			t2.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(count);
	}
	
	
	
	class MyWork implements Runnable{

		@Override
		public void run() {
			for(int i=0;i<100;i++){
				increment();
			}
		}
		
	}
}
