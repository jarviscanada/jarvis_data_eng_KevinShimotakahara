# Kevin Shimotakahara . Jarvis Consulting

I just completed my graduate studies at the University of Ottawa, obtaining a Masters of Applied Science in Electrical and Computer Engineering. My areas of expertise include renewable energy systems, smart grids, mobile communications, and applying machine learning techniques to solving problems in such disciplines. I qualified for $76,000 in scholarships and NSERC CREATE stipends during my two years at the University of Ottawa, including the nationally recognized NSERC CGS-M scholarship. My thesis topic was on improving the performance of device-to-device mobile communications via reinforcement learning techniques to make them suitable for smart grid applications. My reinforcement learning algorithm improved the communication performance beyond what was previously possible with the existing LTE standard. My work has resulted in five publications, two of which being journals of the Institute of Electrical and Electronics Engineers (IEEE Access and IEEE Systems Journal). I also have some industry data science experience, having developed electricity price forecasting neural networks with BluWave~AI, and a reinforcement learning based electric vehicle charging controller for the world renowned Fraunhofer Institute for Solar Energy Systems in Freiburg, Germany.
Despite recognizing the need for rapid development and deployment of energy system solutions to accelerate the global energy transition from a fossil fuel based economy to one driven by sustainable sources, the work I enjoy most is solving problems with computers while applying principles of discrete mathematics. As such, I am currently seeking rich opportunities to expand my skills in software development to a professional level, then combine them with my existing domain knowledge of renewable energy systems to foster an outstanding career in applying data science and software development practices to modernize the way we source, distribute, and consume electricity.

## Skills

**Proficient:** Java, Linux/Bash, RDBMS/SQL, Agile/Scrum, Git, Reinforcement Learning Algorithm Design, Microsoft Office

**Competent:** Spring, Apache Maven, JUnit/Mockito, RESTful APIs, Python, Matlab, Microsoft Excel/Access VBA, Time Series Data Analysis, Overleaf (LaTeX)

**Familiar:** JDBC, Assembly Language, Microcontrollers, Computer Processor Fundementals, i.e. How to Build out of Digital Electronics Components, C/C++

## Jarvis Projects

Project source code: [https://github.com/jarviscanada/jarvis_data_eng_KevinShimotakahara](https://github.com/jarviscanada/jarvis_data_eng_KevinShimotakahara)


**Cluster Monitor** [[GitHub](https://github.com/jarviscanada/jarvis_data_eng_KevinShimotakahara/tree/master/linux_sql)]: This project automates the monitoring of a Linux server cluster's activity. Machine usage data and hardware information gets stored in a PostgreSQL database, which is automatically populated by a network of server nodes every minute. The intended use case of this software is to help Linux Cluster Administration teams evaluate their server cluster's activity and compare it with their physical assets to inform their future assets planning decisions (e.g. adding/removing servers).
The database host setup has been automated with shell scripts, and a .sql file has also been written that contains common queries used by system administrators. Moreover, we automatically configure the installation of PostgreSQL with a shell script that creates a Docker container containing PostgreSQL complete with a persistent Docker volume that stores the data managed by the PostgreSQL instance running in the container.

**Core Java Apps** [[GitHub](https://github.com/jarviscanada/jarvis_data_eng_KevinShimotakahara/tree/master/core_java)]:
      
  - Twitter App: This application allows a user to post, read, and delete Tweets from the Linux Command Line Interface. Our Dockerized Java program accepts a Twitter Developer's OAuth 1.0 credentials needed to be serviced by the Twitter REST API, in addition to the information about the post/show/delete Tweet action to be performed as command line inputs. The application then proceeds to contact the Twitter API to fulfill the request, and prints the JSON file(s) containing information about the Tweets that the user just posted/read/deleted.
This project was a rich learning experience, introducing the following essential programming concepts:
Unit/Integration testing with Mockito/JUnit Dependency Management with Spring Organizing project build workflow with Maven Modularizing the functionality of the application as per the Model View Controller (MVC) software development design pattern Reading and writing RESTful Http responses and requests Data parsing and marshalling techniques
  - JDBC App: This application sets up a connection via the JDBC API to a PostGreSQL database that manages a business' customer and order records. The core concept of this project was to develop objects that can streamline the process of building Strings that contain SQL statements which can be fed to JDBC APIs, whereupon the PostGreSQL driver that implements the JDBC interfaces can forward the SQL statements to the database application, and receive its response.
Two different Data Access Objects (DAO) were created, one each for interacting with the Customer and Order data tables in the database. These DAOs leveraged JDBC libraries to facilitate simple SQL query, update, and insert statements specified by a Java client. Data Transfer Object classes were also designed for passing information between the DAOs and the business logic code.
  - Grep App: This application recreates partial functionality of the grep command commonly used in the Linux command line. More specifically, given an input regular expression, directory, and output path/filename, it will search through all files found in the directory and all its sub directories for instances of the regular expression, and print the lines of text containing these instances into the indicated file.
This application uses functional programming techniques for efficient iteration through file systems, and the contents of files themselves.
This project served as a learning experience. Over the course of its development, I learned much about the IntelliJ IDE, Java Regular Expressions, leveraging io libraries for file manipulation, replacing conventional looping techniques with internally iterable stream objects into which lambda functions can be passed to their intermediate and terminal methods, and the Maven project management framework.

**Springboot App** [[GitHub](https://github.com/jarviscanada/jarvis_data_eng_KevinShimotakahara/tree/master/springboot)]: Not Started

**Hadoop** [[GitHub](https://github.com/jarviscanada/jarvis_data_eng_KevinShimotakahara/tree/master/hadoop)]: Not Started

**Spark** [[GitHub](https://github.com/jarviscanada/jarvis_data_eng_KevinShimotakahara/tree/master/spark)]: Not Started

**Cloud/DevOps** [[GitHub](https://github.com/jarviscanada/jarvis_data_eng_KevinShimotakahara/tree/master/cloud_devops)]: Not Started


## Highlighted Projects
**Machine Learning Based Electricity Price Forecasting**: Designed, trained, and tested a regression neural network with Python/TensorFlow/Pandas to forecast the hourly Ontario electricity price (HOEP); neural network forecasts had a 24% lower mean absolute error relative to the Ontario Independent Electricity Systems Operator's prediction models over an entire year's worth of price data.

**Reinforcement Learning Resource Allocation for LTE D2D-Enabled Smart Grid**: Formulated a multi agent Q-learning resource allocation algorithm aimed at minimizing the packet drop rates of smart grid devices in an out of network coverage scenario. The algorithm and engineering models were coded in Matlab/Simulink, leveraging Simscape Power Systems, SimEvents, and LTE System Toolbox. The algorithm outperformed the LTE standard algorithm, demonstrating 20%-40% absolute reductions in packet drop rate and 10-20 ms reductions in latency for all smart grid applications modeled. The results of this project has been published in a peer reviewed IEEE journal. DOI: 10.1109/ACCESS.2019.2920662.


## Professional Experiences

**Software Developer, Jarvis (2020-present)**: Solve classical software design and big data problems with professional grade methodologies.

**Scientific Assistant, Fraunhofer Institute for Solar Energy Systems (ISE) (January 2020 - May 2020)**: Fulfilled a four month research contract aimed to integrate reinforcement learning electric vehicle charging controller logic into Fraunhofer ISE's Multi Agent Energy Management System project. Played a developer role in a GitFlow software development environment, interfacing novel Python scripts with Dockerized REST APIs. Produced simulation results suggesting novel approach could make the microgrid's utilization of local photovoltaic energy generation 16.7% higher than that of conventional charging schemes. As of writing this description, the results of this project will be showcased at the EnergyInformatics conference at the end of October 2020 in Switzerland.

**Assets Management Engineering Co-op, Hydro Ottawa Ltd. (May 2016 - December 2016)**: Designed a database application in Access VBA to determine HOL's distribution system's capacity for connecting distributed energy resources (DER) of all kinds including rooftop photovoltaics. Developed a sustainable information system to power application that accesses regularly updated databases that characterize the nearly 6,000 km of conductors, etc. that constitutes power system. The application automated several steps in DER connectivity impact assessments, resulting in a significant reduction of engineering labor costs associated with connecting renewables to the power grid . Succeeded in laying out the ground work for a means to collect information from heterogeneous structured/semi-structured/unstructured data sources, perform business logic on the data, and present it to the end user via a Microsoft Access front end.

**Research Assistant, Defense Research and Development Canada (May 2015 - August 2015)**: Developed ray tracing programs in Matlab capable of simulating the propagation path of 9-26 MHz radar beams as they refract through the ionosphere. Succeeded in obtaining publishable results in a four month period as a second year undergraduate co-op student. DOI: 10.1109/IRS.2016.7497370


## Education
**University of Ottawa (2018-2020)**, Master of Applied Science (MASc), Electrical and Computer Engineering
- NSERC CGS-M Scholarship
- NSERC CREATE TOPSET Stipend
- NSERC MSFSS Scholarship
- University of Ottawa International Experience Scholarship
- University of Ottawa Ontario General Scholarship
- University of Ottawa Excellence Scholarship
- University of Ottawa Entrance Scholarship
- GPA: 4.0/4.0

**Carleton University (2013-2018)**, Bachelor of Engineering, Electronics
- NSERC USRA
- Carleton Entrance Scholarship
- Dean's List 2013-2018
- 1st Place: Carleton Junior Design Competetion 2014
- GPA: 4.0/4.0


## Miscellaneous
- University of Alberta Reinforcement Learning Specialization
- Hockey player
- Musician - guitar, bass, vocals, songwriting
- Long distance runner, cyclist
- Strategy game enthusiast
- Home cook, design cost and flavour optimized recipes with sustainable ingredients