#任务执行
- 任务通常是一些抽象而且离散的工作单元。通常把应用程序的工作分解到多个任务中，可以简化程序的组织结构，提供一种自然的事务边界来优化错误恢复过程，以及提供一种自然的并行工作结构来提升并发行。

##在线程中执行任务
###串行的执行任务
- 主线程必须处理完当前请求才能处理写一个请求，若请求中存在IO等操作，导致cpu利用率低，服务响应慢

###显示的为任务创建线程
- 看ThreadPerTaskWebServer可知任务处理过程从主线程中分离出来，使得主循环能够更快的重新等待下一个到来的连接，这使得程序在完成前面的请求之前可以接受新的请求，从而提高响应性
- 任务可以并行处理，从而能同时服务多个请求，如果有多个处理器，或者任务由于某种原因被阻塞，程序的吞吐量将得到提高
- 任务处理代码必须是线程安全的，因为当有多个任务时会并发的调用这段代码

###无限创建线程的不足
- 线程生命周期开销非常高：线程的创建和销毁并不是没有代价的。根据平台的不同，实际的开销也有所不同，但线程创建的过程都会需要时间，延迟处理的请求，并且需要JVM和操作系统提供一些辅助操作。如果请求的到达率非常高且请求的
处理过程都是轻量级的，那么为每个请求创建一个新的线程将消耗大量的计算资源。
- 资源消耗：活跃的线程会消耗系统资源，尤其是内存。如果可运行的线程数量多于可用处理器数量，那么有些线程将闲置。大量空闲的线程会占用许多内存，给垃圾回收器带来压力，而且大量的线程在竞争CPU资源时还将产生其他性能的开销。
如果已经拥有足够多的线程使得所有CPU保持忙碌状态，那么再创建更多的线程反而会降低性能。
- 稳定性：在可创建的线线程的数量上存在一个限制。这个限制随着平台的不同而不同，并且受多个因素的制约，包括JVM启动参数，Thread构造函数中请求栈的大小，以及底层操作系统对线程的限制等。如果破坏了这些限制，那么很可能抛出
OutMemoryError异常。

##Executor框架- 任务是一组逻辑工作单元，而线程则是使任务异步执行的机制、

###执行策略

###线程池
- Executors.newFixedThreadPool();将创建一个固定长度的线程池，每当提交一个任务时就创建一个线程，直到达到线程池的最大数量，这时线程池的规模将不再变化，如果某个线程发生了Exception而结束，那么线程池会补充一个新的线程
- Executors.newCachedThreadPool();将创建一个可缓存的线程池，如果线程池的当前规模超过了处理请求时，那么将回收空闲的线程，当需求增加时，可以添加新的线程，线程池的规模不存在任何限制
- Executors.newSingleThreadExecutor();是一个单线程的Executor，它创建单个工作线程来执行任务，如果这个线程异常结束，会创建另一个线程来替代，能确保依照任务队列中的顺序来串行执行，例如FIFO、LIFO、优先级
- Executors.newScheduledThreadPool();创建一个固定长度的线程池，而且以延迟或定时的方式来执行任务

###Executor的生命周期
- ExecutorService继承了Executor，因此也有execute方法
- ExecutorService的生命周期有三种状态：运行、关闭和已终止。ExecutorService在初始创建时处于运行状态。shutdown方法将执行平缓的关闭过程：不再接受新的任务，同时等待已经提交的任务执行完成--包括
那些还未开始执行的任务。shutdownNow方法将执行粗暴的关闭过程：它将尝试取消所有运行中的任务，并且不再启动队列中尚未开始的任务。
- 在ExecutorService关闭后提交的任务将由"拒绝执行处理器（Rejected Execution Handler）"来处理，它会抛弃任务，或者使得execute方法抛出一个未检查的RejectedExecutionException。等所有任务执行完成后，
ExecutorService将转入终止状态。可以调用awaitTermination来等待ExecutorService到达终止状态，或者通过调用isTermination来轮询ExecutorService是否已经终止。通常在调用awaitTermination之后会立即调用shutdown，从而产生
同步的关闭ExecutorService的效果。
- ExecutorService来管理生命周期
-        //Executor是否已经关闭
-        EXECUTOR_SERVICE.isShutdown();
-        //Executor是否终止
-        EXECUTOR_SERVICE.isTerminated();
-        //平缓关闭Executor
-        EXECUTOR_SERVICE.shutdown();
-        //立即粗暴终止Executor
-        EXECUTOR_SERVICE.shutdownNow();
- 终止和关闭的区别，终止是指所有的线程执行完毕，关闭是指将Executor关闭，关闭即不会接受新的任务，也不会执行新的任务

###延迟任务与周期任务
- Timer类负责管理延迟任务以及周期任务，Timer支持基于绝对时间而不是相对时间的调度机制，因此任务的执行对系统时钟变化很敏感，而ScheduledThreadPoolExecutor只支持基于相对时间的调度。
- Timer的问题：1.支持绝对时间调度机制，对系统时间敏感；2.Timer在执行所有定时任务时只会创建一个线程，如果某个线程执行时间过程，那么将破会其他TimerTask的定时准确性；3.如果TimerTask抛出一个未检查的异常，那么Timer将表现出糟糕的行为，会终止定时线程，尚未执行的TimerTask将不会再执行，新的任务也不能被调度
- 找出可利用的并行性
- 如果要构建自己的调度服务，可以使用DelayQueue，它实现了BlockingQueue，并为ScheduledThreadPoolExecutor提供调度功能。DelayQueue管理着一组Delayed对象。每个Delayed对象都有一个相应的延期时间：在DelayQueue中，只有
某个元素逾期之后，才能从DelayQueue中执行take操作。

##找出可利用的并发行

###实例：串行的页面渲染器

###携带结果的任务Callable和Future
- Runnable是一种有很大局限的抽象，虽然能够执行任务，但是不能返回一个值或者异常。
- Callable是一种更好的抽象：他认为主入口点（即call）将返回一个值，并可能抛出一个异常。
- Runnable和Callable描述的都是抽象的计算任务。
- Future表示一个任务的生命周期，Future规范中包含的，隐含意义是，任务的生命周期只能前进不能后退。
- get方法的行为取决于任务的状态（尚未开始、正在运行、已完成）。如果任务已经完成，那么get会立即返回或者抛出一个Exception，如果任务没有完成，那么get将阻塞并直到任务完成。如果任务抛了异常，那么get将该异常封装为ExecutionException并
重新抛出。如果任务被取消，那么get将抛出CancellationException。如果get抛出了ExecutionException，那么可以通过getCause来获得被封装的初始异常。
- 可以通过多种方法创建一个Future来描述任务。ExecutorService中所有的submit方法都将返回一个Future，从而将一个Runnable或者Callable提交给Executor，并得到一个Future用来获得任务执行结果或者取消任务。还可以显示的为某个指定的Runnable或者Callable
实例化一个FutureTask。由于FutureTask继承了Runnable，因此可以将他提交给Executor来执行，或者直接调用他的run方法。
- Executor执行的任务有4个生命周期阶段：创建/提交/开始/完成
-  Future表示一个任务的生命周期，并提供了相应的方法来判断是否已经完成或取消，以及获取任务的结果和取消任务等。
- CompletionService将Executor和BlockingQueue的功能融合在一起，可以将Callable任务提交给它来执行，然后使用类似队列操作的take和poll等方法来获得已完成的结果，而这些结果会在完成时将被封装成Future
- 为任务设置时限
- Future.get的异常处理代码将处理两个可能的问题：任务遇到了一个Exception，或者调用get的线程在获得结果之前被中断。

###CompletionService：Execution和BlockQueue
- 如果向Executor提交了一组计算任务，并且希望在计算完成后获得结果，那么可以保留与每个任务关联的Future，然后反复的调用get方法，这种方法虽然可行，但是却繁琐，可以使用完成服务（CompletionService）
- CompletionService将Executor和BlockingQueue的功能融合在一起。可以将Callable交给他执行，然后使用类似队列上操作的take和poll等方法来获取已经完成的结果
- 多个CompletionService可以共用一个Executor

###为任务设置时限
- 在支持时间限制的Future.get中支持这种需求：当结果可用时，它将立即返回，如果在指定时限内没有计算出结果，那么将抛出TimeoutException。在使用时限任务时需要注意，当这些任务超时后应该立即停止，从而避免为继续计算一个不再使用的
结果而浪费资源。



