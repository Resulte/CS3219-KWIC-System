# CS3219-KWIC-System
KWIC-System implements by Java with socket.

### 项目结构

- KWICSystem：主工程，包含KWIC处理，socket服务端；

- KWICSocktClient： socket客服端；
- input.txt：测试输入文本；
- output.txt：测试输出文本；

### 项目介绍

KWIC 作为一个早年间在 ACM 的 Paper 提出的一个问题，被全世界各个大学软件设计课程奉为课堂的讲义或作业的经典。本文档为 KWIC 索引系统总体 设计说明文档，KWIC 索引系统接受一组有序的行;每一行是一组有序的词，每 个词是一组有序的字符。任何一个行都可以通过反复循环移位。

针对于以下输入:

```
Star Wars
The Empire Strikes Back
The Return of the Jedi
```

KWCI 索引系统将会得到以下输出:

```
Back The Empire Strikes
Empire Strikes Back The
Jedi The Retuen of the
Return of the Jedi The
Star Wars
Strikes Back The Empire
The Empire Strikes Back
The Return of the Jedi
Wars Star
of the Jedi The Return
the Jedi The Return of
```

### 需求概述

- 该软件系统可接受命令行输入，并将输入内容进行循环移位处理并将移位结果输出。
- 该系统软件可以由命令行指定要进行处理的英文文本文件 (.txt) 的目录和 文件名以及输出结果文件的目录和文件名。
- 该软件系统支持 Socket 输入，远程 Socket 客户端输入需要处理的字符串， 本地 Socket 服务端接受远程输入并执行移位操作。
- 该软件系统中各个模块可独立升级
- 该软件系统具有较高的性能
- 该软件系统可修改性较高，如果考虑修改排序顺序、增加处理步骤等，方 便系统更新升级。

### 系统架构

系统支持三种输入工作方式:命令行工作方式、本地文件工作方式以及 Socket 工作方式。对于获得的输入，执行循环移位、排序等处理后输出结果。

具有面向对象架构风格的解决方案将系统分解为七个模块

- LineStorage 模块:负责保存所有字和行的所有字符。

- Input 模块:负责从文件中读取数据并将其存储在 LineStorage 模块
- CircularShifter 模块:负责对存储在 LineStorage 模块中的行进行循环移位。
- Alphabetizer 模块:负责按字母顺序对循环移位进行排序。
- Output 模块:负责打印排序后的 Shifter。
- Socket 模块:负责建立 Socket 服务，获取远程 Socket 客户端的输入并返 回执行结果。
- KWIC主控制模块:负责控制其他模块中方法的调用顺序。

与主/子程序解决方案相反，在面向对象的解决方案中，数据和计算组件之间 不被共享。相反，每个模块都提供一个接口，允许其他组件指通过调用该接口 中的方法来访问数据。例如，LineStorage 模块提供了公共接口，允许其他模块 设置特定行中特定字的字符，读取特定字符，读取、设置或删除特定行中的特 定字，一次性读取整个行等等。

整个系统处理流程详细介绍如下：

- 一个KWIC类的对象控制着整个程序的执行。首先，KWIC对象调用Input 类的一个对象的方法。这个方法的参数是包含行的文件名，以及用来存储 数据的 LineStorage 类的一个对象。
- 输入对象的解析方法从文件中读取数据。对于文件中的每一行，它通过调 用 LineStorage 对象的 addEmptyLine 方法在该对象中创建一个新行。新行创建后，Input 对象解析该行，并将所有的字通过 addWord 方法一个接一 个地传递到新行中。在所有的词和行都被处理完后，解析方法返回。
- KWIC 对象对 CircularShifter 类的一个对象调用 setup 方法。这个方法以存 放字行的 LineStorage 对象为参数，一次性产生所有字行的循环移位。在 设置方法的执行过程中，CircularShifter 对象迭代了 LineStorage 对象中的 所有行。
- 在创建了循环移位后，KWIC 对象调用 Alphabetizer 类的一个对象的 alpha 方法。该方法以 CircularShifter 对象为参数，按字母顺序对 CircularShifter 进行排序。在这个过程中，Alphabetizer 对象多次调用 CircularShifter 类的 getLineAsString 方法。
- 最后，KWIC 对象在 Output 类的一个对象上调用 print 方法。这个方法 以上一步的 Alphabetizer 对象为参数。通过调用 Alphabetizer 对象上的 getLineAsString来迭代Alphabetizer对象的所有行。当打印方法返回时，程 序也就执行结束。

### 系统行为概述

(1)  输入指令选择功能: 输入 1 选择命令行输入功能，跳转到步骤 (2);输入 2 选择文件输入功能，跳转到步骤 (3);输入 3 选择 Socket 输入功能，跳转 到步骤 (4);输入 4 退出程序。

(2)  根据系统提示输入需要转换的字符串，输入完成后回车结束，系统将返回 输入字符串经转换后的字符串。随后返回步骤 (1)。

(3)  根据系统提示输入文件路径和文件名，输入完成后回车结束，系统将文件 内容转换结果输出到指定文件中。随后返回步骤 (1)。

(4) 服务器端开启 Socket，等待客户端连接。此时远程主机运行 SocketClient 程序，与 SocketServer 建立连接。客户端成功连接后将会得到主机 IP 地址 以及端口号信息。在客户端输入字符串，系统将会输出经转换后的字符串。

