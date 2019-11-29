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
- interrupt方法只是改变了线程的中断状态而已，详情解释参见interrupt.jpg
- synchronized可以用于获取实例的锁。java的每个实例都持有一个锁，但同一个锁不可以由两个以上的线程同时获取。这种结构是java编程规范规定的，
java虚拟机也是这样实现的。这是java语言从一开始就提供的所谓的物理锁，java程序并不能改变这种锁的运行。
而用于读取的锁和用于写入的锁所指的锁与使用synchronized获取的锁是不一样的。这并不是java编程规范规定的结构，而是开发人员自己实现的一种结构。这就是所谓的逻辑锁。开发
人员可以通过修改ReadWriteLock类来改变锁的运行。
ReadWriteLock类提供了用于读取的锁和用于写入
- 方法的调用和执行分离：提高响应速度；控制执行顺序（调度）；可以取消和反复执行；通往分布式之路
- 在WorkerThread模式中，不应当让工作线程持有每项工作固有的信息，这些信息应当放在Request角色中。

2019年7月29日 12:13:12 141/523
2019年7月30日 17:45:19 163/523
2019年7月31日 12:00:58 187/523
2019年8月5日 08:30:54 242/523
2019年8月6日 12:14:45 263/523
2019年8月12日 18:24:40 298/523
2019年8月14日 10:14:43 321/523
2019年8月15日 08:25:49 图解多线程模块阅读完成

# 《程序是怎么样跑起来的》
## 对程序员来说什么是CPU
>程序是什么：指示计算机每一步动作的一组指令   
>程序是由什么组成的：指令和数据   
>什么是机器语言：CPU可以直接识别并使用的语言   
>正在运行的程序存储在什么位置：内存   
>什么是内存地址：内存中，用来表示命令和数据存储位置的数值   
>计算机的构成元件中，负责程序的解释和运行的是哪个：CPU--中央处理器   
> CPU内部由寄存器、控制器、运算器和时钟四个部分构成，各部分之间由电流信号相互连通。寄存器可用来暂存指令、数据等处理对象，可以将其看做是
内存的一种，根据种类不同，一个CPU可能会有10-100个寄存器。控制器负责将内存上的指令、数据等读入寄存器，并根据指令的执行结果来控制整个计算机。
运算器负责运算从内存读入寄存器的数据。时钟负责发出CPU开始计时的信号。   
>内存是指计算机的主存储器，简称主存。主存通过控制芯片等于CPU相连，主要负责存储指令和数据。主存由可读写的元素构成，每个字节（1字节=8位）
>都带有一个地址编号。CPU可以通过该地址读取主存中的指令和数据，当然也可以写入数据。   
>程序启动后，根据时钟信号，控制器会从内存中读取指令和数据。通过对这些指令加以解释和运行，运算器就会对数据进行运算，控制器根据该运算结果来控制计算机。   
- 程序是把寄存器当做对象来描述的。
>汇编语言采用助记符（memonic）来编写程序，每一个原本都是电气信号的机器语言指令都会有一个与其对应的助记符，助记符通常为指令功能的简单
>英语单词的简写。汇编语言和机器语言基本上是一一对应的。   
>汇编语言编写程序:mov eax,dword ptr \[ebp-8\] 把数值从内存复制到eax   
>               add eax,dword ptr \[ebp-0Ch\] eax的数值和内存的数值相加   
>               mov dword ptr \[ebp-4\], eax    把eax的数值（上一步相加的结果）存储在内存中   
>从上方汇编语言的示例程序中可看出，机器语言级别的程序是通过寄存器来处理的。eax和ebp表示的都是寄存器的名称。内存的存储场所通过地址编号来
>区分，而寄存器的种类通过名字来区分   
- 不同类型的CPU，其内部内存器的数量、种类以及寄存器存储的数值范围都是不同的。根据功能不同，寄存器大致分为八类。
>累加寄存器：存储执行运算的数据和运算后的数据--只有一个   
>标志寄存器：存储运算处理后的CPU的状态--只有一个   
>程序计数器：存储下一条指令所在内存的地址--只有一个   
>基址寄存器：存储数据内存的起始地址   
>变址寄存器：存储基址寄存器的相对位置   
>通用寄存器：存储任意数据   
>指令寄存器：存储指令。CPU内部使用，程序员无法通过程序对该寄存器进行读写操作--只有一个   
>栈寄存器：存储栈区域的起始位置--只有一个   
- 程序的流程分为顺序执行、条件执行和循环三种。顺序执行是指按照地址内容的顺序执行指令。条件分支是指根据条件执行任意地址的指令。
循环是指重复执行同一地址的指令。
- 函数的调用机制在机器语言中使用call指令和return指令来解决这个问题。函数调用使用的是call指令，将主函数的入口地址设定到程序计数器
之前，call指令会把调用函数后要执行的指令地址存储名为栈的主存内。函数处理完毕后，再通过函数的出口来执行return命令。return命令的功能
是把保存在栈中的地址设定到程序计数器中。在编译高级编程语言的程序后，函数调用的处理会转换成call指令，函数结束的处理则会转成return指令。
- 通过基址寄存器和变址寄存器，可以对内存上特定的内存区域进行划分，从而实现类似于数组的操作。CPU则会把基址寄存器+变址寄存器的值解释为
实际查看的内存地址。变址寄存器的值相当于高级编程语言程序中数组的索引功能
- 机器语言指令的主要类型和功能：
>数据传送指令：寄存器和内存、内存和内存、寄存器和外围设备之间的数据读写操作
>运算指令：用累加寄存器执行算术运算、逻辑运算、比较运算和移位运算
>跳转指令：实现条件分支、循环、强制跳转等
>call/return指令：函数的调用/返回调用前的地址
- IC一个引脚只能表示两种状态，是1位；8位二进制数被称为一个字节，是因为计算机所处理的信息的基本单位是8位二进制数。字节是最基本的信息计量
单位，位是最小单位。
- 用字节单位处理数据时，如果数字小于存储数据的字节数（=二进制的位数），那么高位上就用0填补。
- 对于用二进制数表示的信息，计算机不会区分它是数值、问题、还是某种图片的模式等，而是根据编写程序的各种对计算机发出的指示来进行信息的处理（运算）。
具体进行何种处理，取决于程序的编写方式。
- 二进制和十进制的转换方式
- 移位运算指的是将二进制数值的各进行左右移位的运算。移位有左移（向高位方向）和右移（向低位方向）两种。
- 二进制数中标识负数值时，一般会把最高位作为符号来使用，因此我们把这个最高位称为符号位。符号位是0时表示正数，符号位是1时表示负数。
计算机在做减法运算时，实际上内部是在做加法运算。
- 补数就是用正数来表示负数，我们需要将二进制数的各数位的数值全部取反，然后再将结果加1，例如，用8位二进制数标识-1时，只需要求1，也就是
00000001的补数即可。具体来说就是各数位取反成1，1取反成0，然后再将取反的结果加1，最后转化成11111111
- 对于溢出的位，计算机会直接忽略
- 补数求解的变换方法就是取反+1，将二进制数的值取反后加1的结果，和原来的值相加，结果为0
- 例子：3-5这个运算实际上在计算机中是3+(-5)，3的二进制数是00000011，5的二进制数是00000101，5的二进制取反是11111010，-5的二进制数
是在5的二进制数上取反再加1，那么-5的二进制数是11111011，那么3+(-5)是11111110，最高位是1表示负数，那么这个数取绝对值是，这个数再取反加1，
也就是00000010，也就是2，那么最终11111110表示的值是-2，也就是3-5=-2
- 编程语言中包含的整数数据类型中，有的可以处理负数，有的不可以处理负数，如C语言中的unsignedshort和short类型本别只能表示正数和正负数都能表示，
都是16位，那么一共能表示2的16次幂=65536种值，只能表示正数的能标识0~65536，正负数标识-32768~32767，此处之所以负数比正数多了一个，是因为0被当做正数处理
>右移：当二进制数的值表示图形模式而非数值时，移位后需要在最高位补0，这就称为逻辑右移。将二进制数作为带符号的数值进行运算时，移位后要在
>最高位填充移位前符号位的值，这称为算术右移。如果数值是用布数表示的负数值，那么右移后在空出来的最高位补1，就可以正确的实现1/2，1/4，1/8等
>的数值运算。如果是正数，只需要在最高位补0即可。
>符号扩充：以8位二进制数为例，符号扩充就是指在保持值不变的前提下将其转换成16位和32位的二进制数。将01111111这个正的8位二进制数转换成
>16位二进制数是0000000 0111111，像11111111这样用补数来表示的数值，转成16进制数是11111111 11111111，也就是说不管正数还是用补数表示的
>负数，都只需要用符号位的值填充高位即可，这就是符号扩充的方法。
- 计算机能处理的运算分为两种：算术运算和逻辑运算。算术运算是指加减乘除四则运算。逻辑运算是指对二进制数各数字位0和1分别进行处理的运算，
包括逻辑非（NOT运算）、逻辑与（AND运算）、逻辑或（OR运算）和逻辑异或（XOR运算）四种。

## 计算机进行小数运算时出错的原因
- 标识小数的两种数据类型：双精度浮点数和单精度浮点数，双精度浮点数类型用64位，单精度浮点数是32位来表示全体小数。因为计算机内部使用的是二进制数，
所以基数自然就是2.
- 浮点数是指用符号、尾数、基数和指数这四部分来表示的小数。
- 双精度浮点数（64位）：1位符号部分+11位指数部分+52位尾数部分；单精度浮点数（32位）：1位符号部分+8位指数部分+23位尾数部分
- 符号部分是指用一个数据位来表示数值的符号。该数据位是1时表示负，为0时则表示正或0。数值的大小用尾数部分和指数部分来表示。尾数部分用的是
“将小数点前面的值固定为1的正则表达式”，而指数部分用的则是“EXCESS系统表现”
> 尾数部分使用正则表达式，可以将表现形式多样的浮点数统一为一种表现形式。例如：十进制的0.75有很多中表现形式，为了方便处理，制定一个
>统一的规则。例如，十进制的浮点数应该遵循小数点前面是0，小数点后面第一位不能是0这样的规则，那么0.75就可以表示成0.75*10的0次幂。
>在二进制中，我们使用的是“将小数点前面的值固定位1的正则表达式”。具体说就是将二进制数表示的小数左移或右移（逻辑移位）数次后，整部分的第一位
>变为1，第二位之后都变为0
- 如何避免计算机计算出错：1.回避策略，无视这些错误，微小的误差可以忽略；2.将小数转成整数来计算
- 在以位为单位表示数据时，使用二进制数很方便，但是如果位数太多，看起来就比较麻烦，在实际程序中，经常使用十六进制数代替二进制数。只需要在数值
的开头加上0x就可以表示十六进制数。

## 熟练使用有棱有角的内存
- 栈和队列：队列一般是以环状缓冲区的方式来实现，在数组的末尾写入数据后，后一个数据就会被写入数组的起始位置，这样，数组的末尾和开头就连接起来。
- 链表和二叉树：链表能更加高效的对数组数据进行追加和删除处理；二叉查找树可以更加高效的对数组数据进行检索。
- 二叉查找树是指在链表的基础上往数组中追加元素时，考虑到数据的大小关系，将其分成左右两个方向的表现形式。数值大放在右边，数值小放在左边，但
实际的内存并不会分成两个方向，是在程序逻辑上实现的。

## 内存和磁盘的亲密关系
- 磁盘中存储的程序，必须要加载到内存后才能运行，在磁盘中保存的原始程序是无法直接运行的。这是因为，负责解析和运行恒旭内容的CPU，需要通过
内部程序计数器来指定内存地址，然后才能读出程序。
- 磁盘缓存指的是从磁盘中读出的数据存储到内存空间中的一种方式。
- 虚拟内存是指把磁盘的一部分作为假想的内存来使用。虚拟内存虽说是把磁盘作为内存的一部分来使用，但实际上正在运行的程序部分在这个时间节点
必须在内存中存在，为了实现虚拟内存，就必须把实际内存的内容和磁盘上的虚拟内存的内容进行部分置换。
- 虚拟内存的方法有分页式和分段式。windows采用的是分页式，把内从从磁盘中读出到内存中叫page in，反之叫page out
- 磁盘通过其物理表面划分成多个空间来使用，划分的方式有扇区方式和可边长方式两种。windows中使用扇区方式。在扇区中把磁盘表面分成若干个
同心圆的空间就是磁道，把磁道按照固定大小划分而成的空间就是扇区。扇区是对磁盘进行物理读写的最小单位，在windows逻辑方面对磁盘进行读写的单位
是扇区整数倍蔟。不同的文件是不能存储在同一个蔟中的，否则就会导致只有乙方的文件不能被删除。

## 亲子尝试压缩数据
- 文件存储的最小单位是字节
- 在任何情况下，文件中的字节数据都是连续存储的。
- 把文件内容用数据x重复次数的形式来表示的压缩方法称为RLE（run length encoding 行程长度编码）算法

## 程序是在何种环境中执行的
- 运行环境=操作系统+硬件
- CPU只能解析其自身固有的机器语言，不同的CPU能解释的机器语言种类也是不同，机器语言的程序称为本地代码。程序员用C语言等编写的程序，在编写阶段
仅仅是文本文件。文本文件在任何环境下都能显示和编辑，我们称之为源代码。
- 在windows的应用软件中，键盘输入、显示器输出等并不是直接向硬件发送指令，而是通过向windows发送指令来间接实现的。
- 应用程序向操作系统传递指令的途径称为API。

## 从源代码到可执行文件
- 能够把C语言等高级编程语言编写的源代码转换成本地代码的程序称之为编译器。何种编程语言使用的编译器+编译器生成的本地代码是用于那种CPU+该编译器
是在什么环境下使用的
- 当程序加载到内存后，会额外生成两个组，栈和堆。栈是用来存储程序内部临时使用的变量（局部变量），以及函数调用时所用的参数的内存区域。堆
是用来存储程序运行时的任意数据以及对象的内存领域。
- 内存中的程序就是由用于变量的内存空间、用户函数的内存空间、用于栈的内存空间、用于堆的内存空间这4部分构成的。
- 栈和堆的相似之处在于，他们的内存空间都是在程序运行时的到申请分配的。不过，在内存使用方法上，二者存在些许不同。栈中对数据进行存储和舍弃（清理处理）
的代码，是由编译器自动生成的，因此不需要程序员的参与。使用栈的数据的内存空间，每当函数被调用时都会得到申请分配，并在函数处理完毕后自动释放。
堆的内存空间，则要根据程序员编写的程序来明确进行申请分配或释放。如C语言通过malloc()申请堆内存够，free()释放堆内存；C++中使用new申请分配堆内存，使用
delete来释放堆内存。
- 编译和链接生成可执行文件

## 操作系统和应用的关系
- windows操作系统的主要特征：1.32位操作系统；2.通过API函数集来提供系统调用；3.提供采用了图形用户界面的用户界面；4.通过WYSIWYG实现打印输出
5.提供多任务功能；6.提供网络功能及数据库功能；7.通过即插即用实现设备驱动的自动设定
- 32位是指系统可以一次性处理32位的数据
- 中间件：处于操作系统和应用的中间的软件，如数据库，ActiveMQ
- 程序可以根据功能分为操作系统、中间件、应用

## 通过汇编语言了解程序的实际构成
- 汇编语言是通过助记符来记述程序
- 汇编语言通过跳转指令实现循环和条件分支
- 即使使用汇编语言编写的源代码，最终也必须要转换成本地代码才能运行，负责转换工作的程序称为汇编器，转换这一处理本身称为汇编。


2019年8月20日 13:05:05 6/274
2019年8月21日 10:13:42 19/274
2019年8月21日 11:17:15 27/274
2019年8月22日 16:48:38 31/274
2019年8月22日 17:55:52 35/274
2019年8月27日 08:23:29 50/274
2019年8月27日 12:09:24 59/274
2019年8月27日 17:30:01 81/274
2019年8月29日 12:23:07 103/274
2019年9月3日 16:40:02 153/274
2019年9月3日 18:28:19 173/274
2019年9月3日 19:54:04 229/274

# java多线程编程内容-核心篇 


-- 2019年11月5日11:08:14 
-- 2019年11月12日12:25:22 28/480
-- 2019年11月12日 21:12:33 53/480
-- 2019年11月13日 18:40:09 67/480
-- 2019年11月18日 17:36:22 85/480
-- 2019年11月25日 12:01:56 87/480