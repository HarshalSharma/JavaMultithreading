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
<b>4. Locks:</b>
  - <b>ReEntrantLocks</b>: Renetrant locks are locks which can be locked many times and require that much unlock for the thread to continue, can be used in cases where multiple threads are working on the same unit of work and a kind of synchronization is required between them.
  - <b>Semaphores</b>: semaphores are like locks on resources or something that is available only in quantity managed by semaphores. They are used to control access to some resource, a one permit semaphore is like a lock, so once a thread have acquired and no more semaphores are available then other threads have to wait.
  - <b>Deadlock</b>: If you lock lock1 then lock2 in first thread and lock2 first in second thread then lock 1, it would result in deadlock, because both threads are waiting for locks which are already locked by them.
  because the first thread have already taken lock over the first lock and it is not ready to give it up and similar situation is for the second lock.
  
  we can solve it by always having our lock in same order OR
  we can write a method which try to acquire both locks if it succeeds then we return else
  we wait for some time and try to acquire the locks again.
 
<b>3. callableAndFuture:</b>
  - <b>CallableAndFuture</b>: To get a return result from a thread on completed it's work, we can use Callable and Future.
  - <b>InterruptingThreads</b>: Thread Interruption with basic thread.interrupt method, and how to handle interruption gracefully.
  - <b>InterruptingThreadsInPools</b>: Interuppting many threads in executors.




-----------------------
Written By Harshal
