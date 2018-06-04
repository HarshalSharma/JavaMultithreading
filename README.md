<b>This project contains implementation basics of Multithreading in Java.</b>

Description:

<b>1. StartingThreads:</b>
  - <b>App1</b>: Create a very basic Thread which can run Asynchronously, by inheriting the Thread class. 
  - <b>App2</b>: Decouple Work from Thread by implementing the Runnable interface and allowing your class to be run by a separate thread.
  - <b>SynchronizedKeyword</b>: Make access to any method synchronized with the keyword.
  - <b>MultipleLocks</b>: Achieve synchronization on a different block of code by different locks(mutex). 
  - <b>MultipleLocks2</b>: Achieve synchronization using a synchronized Collection.


<b>2. Thread Pools:</b>
  - <b>ThreadPools</b>: Submit a bunch of tasks/runnable to Executors of different kind and size.
  - <b>CountDownLatches</b>: Use countdown latches who stay synchronized and keep track of how much work is done or have to do.


<b>3. ProducerConsumer:</b>
  - <b>ProducerConsumer</b>: Using BlockingQueue implemented a Producer and Consumer pattern, where Producer wait for space in BlockingQueue and Consumer consume data from BlockingQueue after fixed intervals.
  - <b>WaitAndNotify</b>: Using wait and notify method with Intrinsic locks/monitor locks to set thread synchronization between consumer and producer.
  - <b>LowLevelSync</b>: Implementing Producer and consumer with basic LinkedList, counter and a lock.




-----------------------
Written By Harshal
