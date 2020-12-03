# Introduction
[Previously](https://github.com/jarviscanada/jarvis_data_eng_KevinShimotakahara/tree/master/python_data_wrangling),
Jarvis Consulting Group provided a proof-of-concept data analytics solution to London Gift Shop, an online e-commerce store
that needed data-driven insight to bolster their marketing campaigns.
Our solution imported customer transaction data into a PostgreSQL data warehouse, where it could be accessed by a Jupityr notebook
for processing with Pandas DataFrames. London Gift Shop was pleased with the results, and requested that our solution be scaled
to fit the data analytics needs of their entire company.

To do this, we could no longer assume that input data could fit into the memory of a single machine, making our
previous Pandas-based methodology no longer feasible. The solution to this problem was to 
partition large data files across a cluster of many machines, and process the partitions with distributed algorithms capable of generating
the same results as before. Fortunately, Microsoft Azure Databricks provides a Software as a Service (SaaS) solution
that can set up such a distributed computing environment for us, complete with a user-friendly notebook interface
where we can write big data analytics code in PySpark. PySpark is a Python language API for Apache Spark, which is currently the most popular
big data execution engine. With it, we can use the Spark DataFrame Structured API, which abstracts away the complicated details related to
our data being fragmented across a cluster of machines, and allows us to work with data tables in much the same way we were used to
when using Pandas.

Before delving into Databricks, we first needed to study the fundamentals of Spark; to do this, we tested the technology on the
Hadoop cluster implemented [previously](https://github.com/jarviscanada/jarvis_data_eng_KevinShimotakahara/tree/master/hadoop),
writing queries with PySpark to analyze World Development Indicator data in a Zeppelin notebook.

# Azure Databricks Implementation

## Description of Input Data
The data set we are working with in Databricks consists of two years' worth of transaction data stored in a .sql file.
Its contents have been summarized in the following table:

Field Name | Data Type | Description
---------- | --------- | -----------
invoice_no | string    | Identifies the invoice the record belongs to
stock_code | string    | Identifies the item purchased
description| string    | Describes the item purchased
quantity   | integer   | Counts how many items of the same stock_code were purchased
invoice_date | timestamp | The date and time that the transaction occurred
unit_price | float     | Unit price of item purchased
customer_id| integer   | Identifies the customer who made the purchase
country    | string    | The country the customer lives in

It should be noted that the "invoice_no" field is deliberately formatted as a string
because the values are alphanumeric despite its name implying it is just a number.

## Description of Data Analytics Workflow
The majority of the work performed in this project was writing DataFrame queries in PySpark to answer business questions related to the company's
revenue stream, purchase statistics, and customer behaviour. However, some setup was required to injest the .sql data stored in our PostgreSQL data
warehouse into our data cluster, and start using Azure Databricks for our Spark code development environment and underlying information system. The
main steps to our methodology has been listed below:

1. Extract the retail data file from PostgreSQL in CSV format
2. Subscribe to Microsoft Azure and gain access to an Azure Databricks workspace
3. Create a data cluster with the Azure Databricks Cluster UI
4. Injest the retail data CSV file into Azure Storage with the Azure Databricks Data UI
5. Design a Spark DataFrame schema for the CSV data, and save it to the Hive Metastore used by Spark to interpret source data
6. Import the source CSV data using the developed schema into a DataFrame, and perform DataFrame queries to perform the following analytics:
   * Mean, Median, Mode, Max, Min money spent by customers in a single purchase
   * Monthly volume of placed and cancelled orders
   * Monthly sales
   * Monthly sales growth    
   * Quantification of new and existing users making purchases each month    
   * Recency, Frequency, Magnitude (RFM) analysis to determine which marketing techniques should be applied to which customers based on their consumption habits

## Data Analytics System Architecture
The diagram below provides a high level overview of how the different technologies used in this project relate to one another, and how the information flows between them:

![my image](./assets/SparkDatabricks.png)

There are three main components to our information system, namely the Azure Databricks Workspace, Azure Storage, and Azure Virtual Machine. 

Azure Databricks Workspace is a manifestation of the Databricks application installed on Azure cloud computing resources. 
Databricks provides a complete solution to the setup of a big data analytics coding environment, offering a suite of user interfaces that make data ingestion and
cluster setup a point and click process. Moreover, Databricks also offers a notebook development environment complete with an underlying implementation of Apache Spark
on the automatically provisioned data clusters. Another useful component of Databricks
is the Databricks Distributed File System (DBFS), which abstracts away the implementation details of the underlying data storage mechanism used to manage the data nodes of the cluster. Through the DBFS interface, the user can find and interact with files stored on data nodes with simple path names (i.e., "dbfs:/...") regardless of what software is implementing the distributed storage system, be it a Hadoop Distributed File System, Azure Storage, S3, etc.. In our case, we are using Azure Storage.

The Spark Application is implemented by Databricks, and runs on virtual machines
belonging to a data cluster provisioned by Azure Virtual Machine. Within this data cluster, there are different Java Virtual Machines (JVMs) running that constitute the different components of the Spark Application. The Driver Process serves as the core of the Spark Application, as it maintains information about the Spark Application; responds to the user input fed to it through the Databricks notebook interface; and performs analysis on the work the Spark execution engine must perform, figuring out how to break down the work into jobs, stages, and tasks to be sent to execution nodes that operate on data partitions in the cluster. The Spark Driver makes use of a Hive Metastore to manage the table schema used to interpret the source data so it can be manipulated and represented properly by the Spark Application. The Cluster Manager manages JVM resources in the cluster, allocating them as required to the Spark Application.

## Databricks Notebook
[Link to .ipynb file](https://github.com/jarviscanada/jarvis_data_eng_KevinShimotakahara/blob/develop/spark/notebook/RetailDataAnalyticswithPySpark.ipynb) 
![my image](./assets/databricksScreenShot.png)

# Zeppelin/Spark Over YARN/Hadoop Implementation
- Describe the dataset and your analytics work (make sure you create a link to your ipynb)
## Description of Input Data
The data set we are working with in Zeppelin/Spark/YARN/Hadoop consists of World Developer Index information for the different countries of the world stored in a .csv file.
Its schema has been summarized in the following table:

Field Name | Data Type | Description
---------- | --------- | -----------
year       | integer   | Year the indicator metric corresponds to
countryName| string    | Country the indicator metric corresponds to
countryCode| string    | Country code
indicatorName| string  | Description of what the indicator metric represents
indicatorCode| string  | Unique ID for the indicator metric
indicatorValue| float  | The value of the indicator metric

## Description of Data Analytics Workflow
The setup of the Hadoop cluster and Zeppelin UI was performed in [a previous project](https://github.com/jarviscanada/jarvis_data_eng_KevinShimotakahara/tree/develop/hadoop). Ultimately, Google Cloud Project (GCP) Dataproc was used to build a VM cluster with HDFS/YARN installed on it, and the WDI CSV data was manually ingested from Google Storage into the HDFS via an SSH connection into the master node. GCP was also used to access a Zeppelin web interface to write Hive queries that can ultimately be translated to Mapreduce or Tez jobs to run analytics on our distributed data file.

We reuse this setup as an excercise to acquaint ourselves with the PySpark API, figuring out how to convert SQL queries we are already comfortable with into Spark DataFrame queries that do the same thing. To use PySpark in Zeppelin instead of Hive, we simply need to specify the appropriate interpreter for each paragraph in the Zeppelin notebook. In the upcoming notebook screenshot, you will observe the following queries being made using both SQL and PySpark DataFrames:

1. Historical Annual GDP Growth of Canada
2. GDP for Each Country Sorted by Year
3. Highest GDP for Each Country

## Data Analytics System Architecture
The diagram below provides a high level overview of how the different technologies used in this project relate to one another, and how the information flows between them:

![my image](./assets/HadoopZeppelin.png)

Inside the master node, there exists a layered application architecture. The lowest layer is the HDFS Name Node entity, which is used to manage the storage of data among the HDFS Data Nodes hosted on the worker nodes in our cluster. The state of the HDFS can be interacted with either by tasks running in YARN containers (more on this later) or directly by a person using a web user interface (WebUI). Ultimately, the HDFS offers a scalable solution to storing lots of massive files, by breaking them up into 128 Mb chunks and storing multiple redundant copies of these chunks over multiple commodity-grade computers. The benefits of this approach are twofold: one can scale data storage horizontally with cheap hardware, while also enabling the parallel execution of tasks.

The HDFS is operated on by the "YARN layer", which manages the computational resources in the Hadoop cluster. On the master node, there is a YARN Resource Manager that provisions computational resources for "Application Masters", which are fed by YARN clients and are in charge of running application tasks. Application Masters do this by asking the YARN Resource Manager for computational resources in the form of "containers", where each container is a Java Virtual Machine (JVM) that can run some program, or "task" needed for the application to function. Once granted containers, the Application manager tells the appropriate YARN Node Manager(s) to run their tasks for them.

The "YARN client" previously mentioned passes "execution engine" (e.g. MapReduce, Tez, Spark) jobs to the YARN resource manager, and in our case the Spark Application as a whole is passed to YARN, whereupon a Spark Driver and Executor nodes are created in containers in a rather involved process that the details of which are beyond the scope of this project. In abstract terms, all that needs to be understood is that YARN aids in the allocation of JVM resources required to run the Spark Application, which consists of a Driver JVM and Executor JVMs. The role of the Driver and Executor nodes have already been described in the previous architecture section, and are no different here.

## Zeppelin Notebook
![my image](./assets/zeppelinSparkScreenShot.png)

# Future Improvement
This project only scratched the surface of what Spark/Databricks has to offer. Future work that would be valueable to perform includes but is not limited to:
- Plot invoice price distribution, RFM groupings, monthly time series data
- Try out some Spark high level APIs, e.g. Machine Learning and Data Streaming
- Experiment with RDD low-level API
- Recast PySpark code into equivalent Scala code, compare performance
- Investigate all the features of the Spark UI, try using it to better design data queries
