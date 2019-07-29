# 《图解java多线程设计模式》
- synchronized锁住的是当前对象实例，也就同一个对象多个方法使用synchronized修饰，同时只能有一个线程获取到锁，串行操作
- synchronized静态方法是使用当前类对象的锁来执行线程的互斥处理
- 死锁产生原因：1.存在多个共享资源角色；2.线程在持有某个共享资源的锁的同时，还想获取其他共享资源角色的锁；
3.获取共享资源角色的锁的顺序并不固定
- 在java中可以使用wait，notify和notifyAll实现线程之间的通信，线程在运行的时候，如果发现某些条件没有被满足，可以调用wait方法暂停自己的执行，
并且放弃已经获得的锁，然后进入等待状态。当该线程被其他线程唤醒并获得锁后，可以沿着之前暂停的地方继续向后执行，而不是再次从同步代码块开始的地方
开始执行。**但需要注意的一点是，对线程等待的条件的判断要使用while而不是用if来判断，这样在线程被唤醒后，会再次判断条件是否真正满足**
- 当正在wait的线程调用interrupt方法时（即线程被取消执行时），该线程会在重新获取锁之后，抛出InterruptedException异常。在获取锁之前，线程不会
抛出InterruptedException异常。
- notify和interrupt方法的差别：
> notify和notifyAll是java.lang.Object类的方法，唤醒的是该实例的等待队列中的线程，而不是直接指定的线程。
notify和notifyAll唤醒的线程会继续执行wait的下一条语句。另外，执行notify和notifyAll时，线程必须要获取实例的锁。
interrupt方法是java.lang.Thread类的方法，可以直接指定线程并唤醒。当被interrupt的线程处于sleep或wait中时，会抛出interruptException异常。
>执行interrupt时并不需要获取要取消的线程的锁。
>- interrupt方法只是改变了线程的中断状态而已，详情解释参见interrupt.jpg
2019年7月29日 12:13:12 141/523