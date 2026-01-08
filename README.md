**Multithreading and Concurrency in Java (LLD Focused)**

This repository demonstrates multithreading and concurrency in Java with a strong focus on Low Level Design (LLD) problems and modern Java concurrency concepts.
It is intended for backend engineers and interview preparation, covering both classical threading models and modern Java features up to Java 25 concepts.

**Purpose**

The purpose of this repository is to help developers:

* Understand core multithreading concepts in Java
* Design thread safe systems using LLD principles
* Implement real world concurrency patterns
* Learn modern Java concurrency tools
* Prepare for LLD and multithreading interviews

**Core Multithreading Concepts Covered**

* Thread lifecycle and states
* Thread creation using Thread, Runnable, Callable
* Synchronization using synchronized keyword
* Locks and ReentrantLock
* Volatile and memory visibility
* Wait and notify mechanism
* Deadlock, livelock and starvation
* Atomic variables
* Concurrent collections

**Executor Framework**

The repository includes detailed examples using the Java Executor framework:

* ExecutorService
* ScheduledExecutorService
* Fixed, cached and work stealing thread pools
* Task submission and cancellation
* Graceful shutdown and resource management

**Virtual Threads and Modern Java Concurrency**

This repository includes modern Java concurrency concepts introduced through Project Loom and beyond:

* Virtual threads
* Platform threads vs virtual threads
* Executors using virtual threads
* Blocking IO with virtual threads
* Structured concurrency concepts
* Scalable design using lightweight threads

**Java 21 and above is recommended. Concepts are aligned with Java 25 evolution.**

**Scatter Gather Pattern**

The repository includes Low Level Design implementations of the scatter gather concurrency pattern:

* Parallel task execution
* Aggregating results from multiple services
* Timeout handling
* Partial failure handling
* Executor based implementation
* Virtual thread based implementation

This pattern is commonly used in API gateways, search systems and microservice orchestration.

**LLD Problems Based on Multithreading**

The following Low Level Design problems are implemented with a focus on thread safety and performance:

* Design Prod Ready Pub Sub Library
* Design Push based(Rabbit MQ) Pub Sub Library
* Design Prod Ready Elevator handling all the edge cases.


Each problem focuses on class design, synchronization strategy, and scalability.


**Who Should Use This Repository**

* Backend Java developers
* Engineers preparing for LLD interviews
* Developers working on concurrent systems
* Engineers learning modern Java concurrency
* Developers upgrading from traditional threading models

Java 21 or higher is recommended for virtual thread examples.

**Java Version Coverage**

* Java 8 and above for Executor framework basics
* Java 11 and above for CompletableFuture improvements
* Java 17 as a stable LTS baseline
* Java 21 and above for Virtual Threads
* Concepts aligned with Java 25 concurrency evolution
