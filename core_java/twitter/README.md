# Introduction
This application allows a user to post, read, and delete
Tweets from the Linux Command Line Interface. Our Dockerized
Java program accepts a Twitter Developer's OAuth 1.0 credentials
needed to be serviced by the Twitter REST API, in addition to
the information about the post/show/delete Tweet action
to be performed as command line inputs. The application then
proceeds to contact the Twitter API to fulfill the request,
and prints the JSON file(s) containing information about the
Tweets that the user just posted/read/deleted.

This project was a rich learning experience, introducing the
following essential programming concepts: 

- Unit/Integration testing with Mockito/JUnit
- Dependency Management with Spring
- Organizing project build workflow with Maven
- Modularizing the functionality of the application as per
  the Model View Controller (MVC) software development design
  pattern
- Reading and writing RESTful Http responses and requests
- Data parsing and marshalling techniques

# Quick Start
- how to package your app using mvn
- how to run your app with docker and explain options

# Design
##UML Diagram

This project follows an MVC design pattern; however, we do
not implement a "View" component, and only print JSON files
received from the API in the command line. The MVC design
pattern can be thought of as modelling your application as
an application stack, where each layer is implemented with its
own dedicated class. The different layers specified in the MVC
design pattern (that we implement) are
the Controller layer, the Service layer, and the Data Access
Object (DAO) layer. Moreover, we developed an "Application" layer
on top of the MVC architecture that interfaces the CLI with the
services that the MVC components provide. 
A brief description of the general role
of each layer followed by what actions these principles
translated to in our implementation is included below.

###TwitterCLIApp (Application layer)
This class manages all the dependencies of the various classes
developed in this project, so it can instantiate the final
application class whose methods can be used to send requests
to the Twitter API. This class contains our project's "main" method,
which is called to execute our application.
It accepts the arguments fed to it by the user through the CLI,
reading the requested command to be executed (i.e. "post", 
"show", or "delete"), and calling the appropriate method of
the MVC controller object (TwitterController). the Tweet
object(s) returned by the controller then get(s) printed
in JSON format.

###TwitterController (MVC Controller Layer)
In general, an MVC Controller is responsible
for processing the user input into a format
that can be understood by the Service layer.

In our application, we designed a class called
TwitterController, which parses information 
contained within the input Strings that were
forwarded from the CLI by the Application layer.
For example, when a "post" command is received,
the controller must extract the
status update text and coordinate data from the
input argument String Array, and use it to populate a
new Tweet object's text
and coordinate fields. Then, the controller
passes the Tweet object to the service layer for further
processing.

###TwitterService (MVC Service Layer)
In general, the MVC Service layer handles the "business 
logic" of the application, which is essentially all the
code that generates the value of the application (e.g.
it performs data analytics calculations, decides how to
respond to the user's input, etc.)

The MVC Service layer is best described in terms of what
it *doesn't* do. First, it is not responible for 
interpreting the user inputs; it relies on the Controller
to tell it what functions to execute, and the arguments
it should use. Information is exchanged between the Controller
layer and Service layer via Data Transfer Objects (DTOs).

Second, it is not responsible for interacting
with the data resources that are used in the project; if
the service layer needs to give/take data to/from a
resource (a REST server in this case), it uses the services
of the DAO layer by sending/receiving data to/from it
in the form of "Data Access Objects (DAOs)". This way,
the business logic code is not cluttered with low level
data access and input parsing code, making it easier to
focus on the actual problem the application is trying to
solve.

In our case, the business logic is simple. TwitterService
performs some basic data validation operations (e.g. confirms
the status update is 140 characters or less prior to posting
the tweet, confirms the coordinates are valid, etc.), and
also filters Tweet objects returned by the "show" operation
to contain only the data fields that the user specifies with
the "fields" input parameter. Also, DAOs and DTOs have the same
structure in this project, namely the Tweet object.

###TwitterDAO (MVC DAO Layer)
In general, the MVC DAO layer is responsible for:
1. Accepting Data Access Objects from the Service layer,
and relaying them to the appropriate data resource (in this
case, the Twitter REST API).
2. Extracting data desired by the Service layer from the
appropriate resource (in this case, the Twitter REST API),
wrapping it in a Data Access Object, and giving it to the
Service layer.

In this project, the TwitterDAO class was designed to implement
the DAO layer, and leverages HttpClient objects to interact
with the REST API. TwitterDAO is capable of generating the URI
needed to feed to the HttpClient objects, and process the
response objects returned into DAOs.                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                            

# Models
Twitter uses a "Tweet Object" to contain the information
of a Twitter status update/tweet made on their platform.
This Tweet object contains a large number of information 
fields; however, this project only cares about a small
subset of the fields available in the Tweet objects
returned by the Twitter API.

Below is a list of the Plain Old Java Objects (POJOs)
we use to store the relevant Tweet Object information, 
and the data fields they contain. Concise descriptions
of what each field is complete with examples can
be found on the Twitter Developer website. Links will
be provided at the end of each POJO list.

**Tweet Object**
- created_at
- id
- id_str
- text
- entities (**Entities Object**)
- coordinates (**Coordinates Object**)
- retweet_count
- favourite_count
- retweeted

Find field descriptions at: 
https://developer.twitter.com/en/docs/twitter-api/v1/data-dictionary/overview/tweet-object

**Entities Object**
- hashtags (**Hashtags Object**)
- user_mentions (**User Mentions Object**)

On Twitter Entities: 
https://developer.twitter.com/en/docs/twitter-api/v1/data-dictionary/overview/entities-object#entitiesobject

**Hashtags Object**
- text
- indices

Find field descriptions at:
https://developer.twitter.com/en/docs/twitter-api/v1/data-dictionary/overview/entities-object#hashtags

**User Mentions Object**
- name
- indices
- screen_name
- id
- id_str

Find field descriptions at:
https://developer.twitter.com/en/docs/twitter-api/v1/data-dictionary/overview/entities-object#mentions

# Spring
- How you managed the dependencies using Spring?

# Docker
- How did you dockerize your app.

# Improvements
- at least three improvements