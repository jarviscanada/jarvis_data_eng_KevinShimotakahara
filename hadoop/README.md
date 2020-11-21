Table of contents
* [Introduction](#Introduction)
* [Hadoop Cluster](#Hadoop-Cluster)
* [Hive Project](#Hive-Project)
* [Improvements](#Improvements)
# Introduction
- Purpose of this project (please see the project kick-off ticket)
- What you have learned or evaluated
- Talk about the hadoop cluster, tools, and Hie project

This project offered a rich learning experience, allowing me to implement a microservice
based software architecture with SpringBoot. Many different technologies and software
design concepts were used to develop this application, including the following:
 - 

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
in our cluster. The state of the HDFS can be interacted with by either
tasks running in YARN containers (more on this later), or directly
by a person using a web user interface (WebUI).

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
with data in a HDFS much like interacting with a RDBMS through SQL commands.
Hive manages table metadata required to serialize and deserialize
data to/from tabular objects readable by code and files stored
in the HDFS. A "metastore" microservice provisions such functionality,
and makes use of a dedicated RDBMS to store the table metadata used by
Hive.

Finally, 



(listify this) The Hadoop Cluster was set up using Dataproc from Google Cloud Platform, and it contains 1 master node and 2 worker nodes. The master node and worker nodes are all configured to have 2 CPUs and a 12GB RAM with 100GB disk size. 
The HDFS replication factor is set to 2, and YARN is configured to have 4 cores and 12GB RAM.

# Hive Project
- discuss the purposes of the project and what you have done.

![my image](./assets/zeppelin.png)
- Post your Zeppelin Notebook screenshot here
	- Make sure your Notebook is nice and clean as hiring managers will visit your project
	- use `Full Page Screen Capture` chrome extention to capture a webpage as a picture

# Improvements
If you have more time, what would you improve?
- at least three improvements