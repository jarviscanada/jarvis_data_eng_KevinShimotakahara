# Introduction
This project deploys a [microservice-based stock trading application](https://github.com/jarviscanada/jarvis_data_eng_KevinShimotakahara/tree/develop/springboot) in two different Kubernetes clusters provisioned by Microsoft Azure Kubernetes Service (AKS) that are maintained with Jenkins Continuous Integration/Continuous Development (CI/CD) pipelines. These clusters provide two distinct "development (dev)" and "production (prod)" environments to practice DevOps paradigms. With the help of Kubernetes, this project is capable of delivering a sophisticated deployment environment that enables our application to be self-healing during contingencies; adaptive to fluctuations in user demand by ensuring that an appropriate number of replicas are running; and capable of evenly distributing workloads across application replicas.

This is a proof-of-concept project aimed at validating the plan to migrate Jarvis' on-premise servers to the Microsorft Azure cloud. 

# Application Architecture

## Kubernetes Architecture (Skip This if You Already Know)
Describing this project's architecture first requires a general architectural description of Kubernetes. Kubernetes is a "container orchestrator", which means it organizes the dispatch of containerized applications/microservices to a distributed system of machines. Kubernetes operates in a master-worker architecture:

![my image](./assets/K8S-Arch.png)

There is a single master node responsible for managing the deployment of containerized applications among the one or more worker nodes in the Kubernetes cluster. Some of the master node's responsibilities include the following:
 - Scheduling the dispatch of new applications/microservices as "pods", which are Kubernetes-control-plane-infused collections of the tightly coupled containers that run your code
 - Monitoring dispatched pods to ensure they are healthy
 - Horizontally scaling containerized applications/microservices by adding/removing replicas of the pods running such microservices as required
 - Performing rolling updates on existing sets of replicated microservices

The master node consists of the following main components:
 - **Controller Manager:** Controllers are threads running background tasks. There are different kinds of controllers named after the system component that they control, namely the Node, Replication, and End-Point controllers, which deal with worker states, pod replications, and service-to-pod connectivity respectively (more on services later).
 - **Scheduler:**  The scheduler figures out where to place pods among your worker nodes based on the current state of the nodes. With each pod, the scheduler takes into consideration their "predicates" and "priorities". Predicates are a mix of system supplied and user supplied constraints that the scheduler must respect while making a pod placement decision, e.g. minimum memory required on the node it will be running in, etc. Priorities are softer design objectives that give an idea of what the pod values in a worker node, enabling it to make the best placement choice among the feasible options remaining after predicate constraints have been taken into consideration.
 - **API Sever:** This is the "front end" of Kubernetes control plane, i.e. it is the interface a system designer uses to set up/observe/manage a Kubernetes cluster. Kubernetes allows us to pass high level commands to its API Server via the `kubectl` command.
 - **etcd:** This is Kubernete's database, which is a key value store; it stores the cluster data required for Kubernetes to function, e.g. job scheduling info, pod details, stage information.

The applications/microservices managed by the master are run on the worker nodes; each worker node is a physical or virtual machine. A worker node consists of the following main components:
 - **Kubelet:** The master uses its API server to speak to a worker node's "kubelet", which is an agent that waits for it to be told to add/remove pods.
 - **Container Runtime:** This refers to the software that creates the containers you build/fill pods with.
 - **Pods:** Pods are groups of containers that share storage, Linux name space, IP addresses, etc. A pod represents one running process in the cluster, and is the smallest unit of stuff that can be interacted with in the cluster.
 - **Kube Proxy:** Essentially, a worker node's kube proxy deals with communication between pods and external entities like internet users.

### On Deployments and Services
Deployment Controllers and Services are used in Kubernetes to manage "pod sets" and the communication between two "pod sets" (as well as between a pod set and some external entity) respectively. A "pod set" in this case refers to a group of identical pods (i.e. a pod running a particular process and all its replicas). Deployment Controllers are used to execute replication, update, rollback, and scaling actions on pod sets. A Service advertises an IP address that can be used to contact a particular pod set. It works by having this IP address being associated with the service, and then the service maintains the list of IP addresses that the current (ephemeral) pods have, then performs some logic (say a load balancing routine) to decide how to forward traffic as it comes in.

## Cloud-Based Project Architecture
For our project, we use a cloud-based implementation of a Kubernetes cluster, namely Microsoft Azure Kubernetes Service (AKS) to run our project's microservices. As shown in the diagram below, we set up an Azure resource group containing an AKS cluster in addition to an Azure Container Registry (ACR) used to store container images used to deploy pods in our AKS:

![my image](./assets/AKS-Arch.png)

The two different microservices that constitute our trading application are the "trading app" and a PostgreSQL database that stores user account information and stock quote data. The "trading app" is a Java-powered web server with a Swagger user interface that users can use to interact with the services our application provides. Both of these microservices have been packaged into Docker images, and pushed to our ACR so they can be accessed by and deployed into our AKS cluster. For the trading app Deployment, we set up a Horizontal Pod Autoscaler Controller, which monitors the CPU usage of its pods, and increases/decreases the number of replicas in the deployment to maintain an optimal level of utilization for each pod. Our trading app replicas are accessible via a public IP address advertized by a Load Balancer Service entity, which allows users to access our web application. The Load Balancer Service forwards any incoming traffic to the pods hosting the server, selecting which pod to forward each request to in a manner that all pods get a similar workload. Finally, a Cluster IP Service was implemented for the PostgreSQL database Deployment so our trading app pods can communicate with it.

# Jenkins CI/CD pipeline
Moreover, a Jenkins helm chart was deployed on a separate Minikube Kubernetes cluster, allowing us to use Jenkins to automate the integration and deployment of our codebase to the AKS cluster when updates to it are made. Using Jenkins in this manner is commonly referred to as building a Continuous Integration, Continuous Deployment (CI/CD) pipeline.
- Describe your CI/CD pipeline (e.g. git clone, build, test, deploy)
- Describe your Jenkins CI/CD pipeline
- Visualize your Ci/CD pipeline with a diagram

# Improvements
1. Implement an automated deployment rollback mechanism (e.g. [blue/green](https://martinfowler.com/bliki/BlueGreenDeployment.html))
2. Design a deployment scheduler in Jenkins to make the CI/CD pipeline provision true Continuous Deployment
3. Some third thing
