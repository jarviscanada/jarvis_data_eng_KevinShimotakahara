# Table of contents
* [Introduction](#Introduction)
* [Hadoop Cluster](#Hadoop-Cluster)
* [Hive Project](#Hive-Project)
* [Improvements](#Improvements)
# Introduction
The Jarvis data analytics team wants to process Big Data, and must 
adopt new technologies to handle it. This project
explores Apache Hadoop (a widely known Big Data platform) on behalf
of the Jarvis data analytics team. Hadoop is an entire ecosystem of
sophisticated tools used to manage and leverage a distributed information
system that can scale to the storage and computational needs of the
most ambitious Big Data projects.

The scope of this project was to try the different tools
the Hadoop ecosystem has to offer by first implementing a small 
Hadoop Distributed File System (HDFS) on a three-node cluster, and 
then using high-level applications and web interfaces to store a large
data file in the HDFS and run queries on it.

The HDFS was implemented using Google Cloud Platform, with YARN as
its computational resource manager. Apache Hive was run on top of YARN,
so we could write SQL-like (Hive Query Language, or HQL)
statements to query data in our cluster. To interact with Hive, we leveraged both
Zeppelin Notebook and Beeline interfaces. Concerning the data we
stored/examined in/with our Hadoop cluster, we chose a 1.8 Gb file
containing World Development Indicator information.

This project offered a rich learning experience; many different 
technologies, concepts, and tools were used to develop this application, 
including the following:
 - Hadoop HDFS
 - YARN
 - MapReduce
 - Spark
 - Apache Hive
 - Hive partitions
 - OpenCSVserde
 - Parquet file format
 - Beeline
 - Zeppelin Notebook
 - Google Cloud Platform/Dataproc

# Hadoop Cluster
At the highest level of abstraction, our cluster consists of a master
machine, and two subordinate worker machines. A user talks to the
master machine to run commands that somehow interact with the
state of the HDFS, and the master node uses the worker nodes to
perform the commands.

Below is an architecture diagram of our Hadoop cluster:

![my image](./assets/Hadoop.png)

Inside the master node, there exists a layered application architecture.
The lowest layer is the HDFS Name Node entity, which is used to manage
the storage of data among the HDFS Data Nodes hosted on the worker nodes
in our cluster. The state of the HDFS can be interacted with either by
tasks running in YARN containers (more on this later) or directly
by a person using a web user interface (WebUI). Ultimately, the HDFS
offers a scalable solution to storing lots of massive files, by
breaking them up into 128 Mb chunks and storing multiple redundant
copies of these chunks over multiple commodity-grade computers.
The benefits of this approach are twofold: one can scale data storage
horizontally with cheap hardware, while also enabling the parallel execution
of tasks.

The HDFS is operated on by the "YARN layer",
which manages the computational resources in the Hadoop cluster.
On the master node, there is a YARN Resource Manager that provisions
computational resources for "Application Masters", which are fed by
YARN clients and are in charge of running application tasks.
Application Masters do this by asking the YARN Resource Manager
for computational resources in the form of "containers", where each
container is a Java Virtual Machine (JVM) that can run some program,
or "task" needed for the application to function. Once granted
containers, the Application manager tells the appropriate YARN Node
Manager(s) to run their tasks for them.
 
The "YARN client" previously mentioned passes "execution engine"
(e.g. MapReduce, Tez, Spark) jobs to the YARN resource manager,
and can be either a person directly interacting with YARN 
via a WebUI, or it can be Apache Hive's "Hive Service", a Java
application that translates Hive Query Language (HQL) commands into
something that an execution engine can understand. This conversion
of HQL to execution engine tasks is the core functionality of Apache
Hive, which is running on our master node. Hive is what makes interacting
with data in an HDFS much like interacting with an RDBMS through SQL commands.
Hive manages table metadata required to serialize and deserialize
data to/from tabular objects readable by code and files stored
in the HDFS. A "metastore" microservice provisions such functionality,
and makes use of a dedicated RDBMS to store the table metadata used by
Hive.

Finally, users can write HQL queries to Hive by using either Beeline,
a CLI-based front end (analogous to PostgreSQL's REPL interface),
or with a notebook-based interface offered by Zeppelin.

This Hadoop Cluster was implemented using Google Cloud Platform's Dataproc service.
Each node is a virtual machine with 2 CPUs, 12 Gb of RAM, and 100 Gb of disk space. 
The HDFS was configured to store 2 copies of each file, 
and YARN was allocated 4 processor cores and 12 Gb of RAM from the cluster.

# Hive Project
The image below is a complete screenshot of the Zeppelin Notebook
created for this project and contains all results, complete with
annotations.

In this notebook, we imported a large file containing half
a century's worth of World Development Indicator data into our HDFS
and used Apache Hive to create a table schema for it, so it could be
queried with HQL commands. A couple of business questions were answered,
e.g. finding out the largest percentage growth in GDP in a single year
each country experienced in recent history, in addition to the year
in which it occurred. We also explored how to properly import CSV data using
OpenCSVserde and views; ways to improve query speeds with Hive partitions and use of the
Parquet column-oriented binary file format; and the difference in query
performance between Hive and Spark.

![my image](./assets/zeppelin.png)

# Improvements
This project only scratched the surface of the Hadoop ecosystem.
As such, there are many more tools and analytics methods to try out,
including but not limited to the following:

1. Comparing MapReduce Vs. Spark Vs Tez execution engines
2. Investigating how our data cluster handles contingencies by shutting
down worker nodes while queries are running
3. Using Zookeeper to implement a hot name node backup, and compare
availability metrics between this approach and simply using a
secondary name node
4. Explore map joins and bucketing techniques in Hive
5. Experiment with submitting jobs to YARN directly
6. Develop an application that uses a Hadoop cluster to store its data
