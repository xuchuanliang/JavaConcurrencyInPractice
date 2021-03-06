#线程池的使用
##在任务与执行策略之间的隐形耦合
- 有些类型的任务需要明确地指定执行策略：依赖性任务、使用线程封闭机制的任务、对响应时间敏感的任务、使用ThreadLocal的任务
###线程饥饿死锁
- 在单线程的Executor中，如果一个任务将另一个任务提交到同一个Executor，并且等待这个被提交的任务的结果，那么通常会引发死锁。第二个任务停留在工作队列中，并等待第一个任务完成，而第一个任务又无法完成，因为
他在等待第二个任务完成。在更大的线程池中，如果所有正在执行任务的线程都由于等待其他仍处于工作队列中的任务而阻塞，那么会发生同样的问题，这种现象被称为线程饥饿死锁。
- 每当提交了一个有依赖性的Executor任务时，要清楚的知道可能会出现的线程饥饿死锁，因此需要在代码或配置Executor的配置文件中记录线程池的大小限制或配置限制。
###运行时间较长的任务
##设置线程池的大小
##配置ThreadPoolExecutor
###线程的创建与销毁
###管理队列任务
- 线程封闭：就是把对象封装到一个线程中，只有这一个线程能看到这个对象，那么这个对象就算不是线程安全的也不会出现任何安全问题，实现线程封闭的方法主要有两种：栈封闭--》简单说就是局部变量，
多个线程访问一个方法，此方法中的局部变量都会拷贝一份到线程栈中，所以局部变量是不会被多个线程共享的，也就不会出现并发问题，所以能用局部变量就别用全局变量，全局变量容易引起并发问题。
ThreadLocal封闭-》使用ThreadLocal是实现线程封闭的最好方法，其实ThreadLocal内部维护了一个Map，Map的key是每个线程的名字，Map的值就是我们要封闭的对象，每个线程中的对象都对应着Map中的一个值，
也就是ThreadLocal利用Map实现了对象的线程封闭。
###饱和策略
- SynchronousQueue：直接将任务从生产者移交给工作者线程，SynchronousQueue不是一个真正的队列，而是一种在线程之间进行移交的机制，要将一个元素放入SynchronousQueue中，必须有另一个线程正在等待接受这个元素。
- JDK提供了几种不同的RejectedExecutionHandler实现，每种实现都包含有不同的饱和策略：AbortPolicy、CallerRunsPolicy、DiscardPolicy和DiscardOldestPolicy
- 中止（Abort）策略是默认的饱和策略，该策略将抛出未检查的RejectedExecutionException，调用者可以捕获这个异常，然后根据需求编写自己的处理代码。当新提交的任务无法保存到队列中等待执行时，抛弃（Discard）策略
会悄悄抛弃该任务。抛弃最旧的（DiscardOldest）策略将会抛弃下一个将被执行的任务，然后尝试重新提交新的任务，如果工作队列是一个优先队列，那么抛弃最久的策略将导致抛弃优先级最高的任务，因此最好不要将抛弃最旧的饱和策略和优先级队列放在一起使用。
- 调用者运行（Caller-Runs）策略实现了一种调节机制，该策略既不会抛弃任务，也不会抛出异常，而是将某些任务退回到调用者，从而降低新任务的流程。他不会在线程池中的某个线程中执行新提交的任务，而是在一个调用了execute的线程中执行该任务。
由于执行任务需要一定的时间，因此主线程至少在一段时间内不能提交任何任务，从而使得工作者线程有时间来处理完正在执行的任务。
###线程工厂
- 每当线程池需要创建一个线程时，都是通过线程工厂方法来完成，默认的线程工厂方法将创建一个新的、非守护的线程，并且不包含特殊的配置信息。通过制定一个线程工厂
方法，可以定制线程池的配置信息
- 如果在应用程序中需要利用安全策略来控制对某些特殊代码的访问权限，那么可以通过Executor中的privilegedThreadFactory工厂来定制自己的线程工厂。通过这种方式创建出的线程，
将与创建privilegedThreadFactory的线程拥有相同的访问权限、AccessControlContext和contextClassLoader。

##扩展ThreadPoolExecutor
- ThreadPollExecutor提供了beforeExecutor、afterExecutor和terminated
- 在执行任务中的线程中将调用beforeExecutor和afterExecutor等方法，这些方法中还可以添加日志、
计时、监视或统计信息收集的功能。无论任务是从run中正常返回，还是抛出一个异常而返回，afterExecutor都会被调用。（如果任务完成后带有一个Error，那么就不会调用
afterExecutor。）如果beforeExecutor抛出一个RuntimeException，那么任务将不被执行，并且afterExecutor也不会被调用。
- 在线程池完成关闭操作时调用terminated，也就是在所有的任务都已经完成并且所有工作者线程都已经关闭后。terminated可以用来释放Executor在其生命周期里分配的各种资源，
此外还可以执行发送通知、记录日志或者收集finalize统计信息等操作。

- ThreadLocal：作用是提供**线程内**的局部变量，这种变量在线程的声明周期内起作用，减少**同一个线程内**多个函数或者组件之间一些公共变量的传递的复杂度。一般情况是private static参见：https://www.zhihu.com/question/23089780

##递归算法的并行化
- 可以使用Executor将串行任务循环转化为并行任务
- 当串行循环中的各个迭代操作之间彼此独立，并且每个迭代操作执行的工作量比管理一个新任务时带来的开销更多，那么这个串行循环就适合并行化。

##总结
- 对于并发任务，Executor是一个强大且灵活的框架，提供了大量可调节的选项，例如创建线程和关闭线程的策略，处理队列任务的策略，处理过多任务的策略（饱和策略），
并且提供几个钩子方法来扩展他的行为。