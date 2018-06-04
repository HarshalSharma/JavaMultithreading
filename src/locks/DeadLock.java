package locks;

import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/*
 * 
 * If you lock1 then lock 2 in first thread and lock 2 then lock 1 in second thread, it would result in deadlock.
 * because the first thread have already taken lock over the first lock and it is not ready to give it up and similar situation is for the second lock.
 * 
 * we can solve it by always having our lock in same order OR
 * we can write a method which try to acquire both locks if it succeeds then we return else
 * we wait for some time and try to acquire the locks again.
 * 
 */
public class DeadLock {

	class Runners{
		
		private Account acc1 = new Account(1000);
		private Account acc2 = new Account(1000);
		
		private Lock lock1 = new ReentrantLock();
		private Lock lock2 = new ReentrantLock();
		
		private void acquireLocks(Lock lock1, Lock lock2) throws InterruptedException{
			while(true){
				boolean isLock1Available = false;
				boolean isLock2Available = false;
				
				try{
					isLock1Available = lock1.tryLock();
					isLock2Available = lock2.tryLock();
				}catch (Exception e) {e.printStackTrace();}
				finally {
					if(isLock1Available && isLock2Available)
						return;
					
					if(isLock1Available)
						lock1.unlock();
					
					if(isLock2Available)
						lock2.unlock();
				}
				Thread.sleep(1);
			}
		}
		
		public void firstThread() throws InterruptedException{
		
			Random rand = new Random();
			for(int i=0;i<1000;i++){
//				lock1.lock();
//				lock2.lock();
				acquireLocks(lock1, lock2);
				try{
					Account.transfer(acc1, acc2, rand.nextInt(100));														
				}catch (Exception e) {
					e.printStackTrace();
				}finally {
					lock1.unlock();
					lock2.unlock();
				}
			}
			
		}
		
		public void secondThread() throws InterruptedException{
			
			Random rand = new Random();
			for(int i=0;i<1000;i++){
//				lock2.lock();
//				lock1.lock();
				acquireLocks(lock2, lock1);
				try{
					Account.transfer(acc2, acc1, rand.nextInt(100));														
				}catch (Exception e) {
					e.printStackTrace();
				}finally {
					lock1.unlock();
					lock2.unlock();
				}				
			}

		}
		
		public void finished() throws InterruptedException{
			System.out.println("Account 1 balance:" + acc1.getBalance());
			System.out.println("Account 2 balance:" + acc2.getBalance());
			System.out.println("Total Balance:" + (acc1.getBalance()+acc2.getBalance()));
		}
				
	}
	
	static class Account{
		
		private int balance;
		
		public Account(int balance) {
			this.balance = balance;
		}
		
		public void withdraw(int amount){
			balance-=amount;
		}
		
		public void deposit(int amount){
			balance+=amount;
		}
		
		public int getBalance(){
			return balance;
		}
		
		public static void transfer(Account a1,Account a2,int amount){
			a1.withdraw(amount);
			a2.deposit(amount);
		}
	
	}
	
	
	
	public static void main(String args[]){
		DeadLock deadLock = new DeadLock();
		deadLock.doTest();
	}
	
	public void doTest(){
		Runners runners = new Runners();
		Thread t1 = new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					runners.firstThread();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		Thread t2 = new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					runners.secondThread();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		
		
		t1.start();
		t2.start();
		
		try {
			t1.join();
			t2.join();
			runners.finished();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	
	
	
	
}
