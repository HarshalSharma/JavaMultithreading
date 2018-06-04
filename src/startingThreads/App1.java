package startingThreads;

class Runner extends Thread{
	public void run(){
		for(int i=0;i<10;i++){
			System.out.println("Count-" + i);
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
}

public class App1 {

	public static void main(String args[]){
		Runner run1 = new Runner();
		run1.start();		
	}
	
}
