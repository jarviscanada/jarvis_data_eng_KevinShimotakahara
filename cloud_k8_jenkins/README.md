# Introduction
This project deploys a [microservice-based stock trading application](https://github.com/jarviscanada/jarvis_data_eng_KevinShimotakahara/tree/develop/springboot) in two different Kubernetes clusters provisioned by Microsoft Azure Kubernetes Service (AKS) that are maintained with Jenkins Continuous Integration/Continuous Development (CI/CD) pipelines. These clusters provide two distinct "development/dev" and "production/prod" environments to practice DevOps paradigms. With the help of Kubernetes, this project is capable of delivering a sophisticated deployment environment that enables our application to be self-healing during continencies; adaptive to fluctuations in user demand by ensuring that an appropriate number of replicas are running; and capable of evenly distributing workloads across application replicas.

This is a proof-of-concept project aimed at validating the plan to running Jarvis' on-premise servers on the Microsorft Azure cloud. 

# Application Architecture
Describing this project's architecture requires describing the architecture of Kubernetes. Kubernetes is a "container orchestrator", which means it organizes the dispatch of Docker containers containing the applications/microservices we want to run on a distributed system of machines. Kubernetes software runs on all cluster nodes, and operates in a master-worker architecture. Ultimately, Kubernetes allows us to pass high level commands to its API via the `kubectl` command 

- Draw a diagram to show your k8s deployment

- Describe the application architecture (e.g. load balancer, auto-scaling, application server, psql, etc.)
- Draw an application architecture (e.g. load balancer, auto-scaling, application server, psql, etc.)


# Jenkins CI/CD pipeline
Moreover, a Jenkins helm chart was deployed on a separate Minikube Kubernetes cluster, allowing us to use Jenkins to automate the integration and deployment of our codebase to the AKS cluster when updates to it are made. Using Jenkins in this manner is commonly referred to as building a Continuous Integration, Continuous Deployment (CI/CD) pipeline.
- Describe your CI/CD pipeline (e.g. git clone, build, test, deploy)
- Describe your Jenkins CI/CD pipeline
- Visualize your Ci/CD pipeline with a diagram

# Improvements
1. Implement an automated deployment rollback mechanism (e.g. [blue/green](https://martinfowler.com/bliki/BlueGreenDeployment.html))
2. Design a deployment scheduler in Jenkins to make the CI/CD pipeline provision true Continuous Deployment
3.
