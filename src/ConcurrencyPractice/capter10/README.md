#避免活跃性危险
- 让线程睡眠尽量不要使用Thread.sleep()方法了，应该是会用TimeUtil类
##死锁
- 当线程A持有锁L并想获得锁M的同时，线程B持有锁M并尝试获得锁L，那么这两个线程将永远等待下去，这种情况是最简单的死锁形式（抱死）
###锁顺序死锁
- 两个线程试图以不同的顺序来获得相同的锁，那么会产生死锁现象。如果按照相同的顺序来请求锁，就不会出现循环加锁依赖性，因此不会产生死锁
- 如果在持有锁时调用某个外部方法，那么将出现活跃性问题。在这个外部方法中可能会获取其他锁（这可能会产生死锁），或者阻塞时间过长，导致其他线程无法及时获得当前被持有的锁。
###开放调用
- 如果在调用某个方法时不需要持有锁，那么这种调用被称为开放调用。依赖于开放调用的类通常能够表现出更好的行为，并且与那些在调用方法时需要持有锁的类相比，也更易于编写。这种通过开放调用来避免
死锁的方法，类似于采用封装机制来提供线程安全的方法：虽然在没有封装的情况下也能够确保构建线程安全的程序，但对一个使用了封装的程序进行线程安全分析，要比分析没有使用封装的程序容易的多。同理，
分析一个完全依赖于开放调用的程序的活跃性，要比分析那些不依赖开放调用的程序活跃性简单。通过尽可能的开放调用，将更易于找出那些需要获取多个锁的代码路径，因此也就更容易确保采用一致的顺序获取锁。

##死锁的避免与诊断
- 在使用细粒度锁的程序中，可以使用一种两阶段策略来检查代码中的死锁：首先，找出在什么地方将获取去到多个锁，然后对所有的这些实例进行全局分析，从而确保他们在整个程序中获取锁的顺序都保持一致。
###支持定时的锁
- 显示使用Lock类中的tryLock功能来代替内置锁机制。当使用内置锁时，只要没有获得锁，就会永远等待下去，而显示锁则可以指定一个超时时限，在等待超出该时间后tryLock会返回一个失败信息。
- JVM可以通过线程转储来帮助识别死锁的发生

##其他活跃性危险
- 要避免使用线程优先级，因为这会增加平台依赖性，并可能导致活跃性问题。在大多数并发应用程序中，都可以使用默认的线程优先级。
- 活锁是另一种形式的活跃性问题，该问题尽管不会阻塞线程，但也不能继续执行，因为线程将不断重复执行相同的操作，而且总会失败。
- 要解决活锁问题，需要在重试机制中引入随机性。
- 活跃性故障是一个非常严重的问题，因为当出现活跃性故障时，除了中止应用程序之外没有其他任何机制可以帮助从这种故障时恢复过来。最常见的活跃性故障就是锁顺序死锁。在设计时应该避免产生锁顺序死锁：确保线程
在获取多个锁时采用一致的顺序。最好的解决方法是在程序中始终使用开放调用。这将大大减少需要同时持有多个锁的地方，也更容易发现一些地方。