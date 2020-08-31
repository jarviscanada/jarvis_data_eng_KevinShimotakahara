#!/bin/bash

#check to see if docker is running; if not, start it
systemctl sudo status docker || sudo systemctl start docker
action=$1
#depending on input, run either "create", "start", or "stop" commands
case $action in
    "create" )
              if [ "$(docker container ls -a -f name=jrvs-psql | wc -l)" = "2" ]; then
                echo "ERROR: Docker container already created. See its info below:"
                docker container ls -a -f name=jrvs-psql
                exit 1
              fi

              db_username=$2
              db_password=$3
              if [ "$db_username" = "" ] || [ "$db_password" = "" ]; then
                echo "ERROR: did not pass db_password and/or db_username." 1>&2
                exit 1
              fi

              #check if pgdata volume exists and create it if it does not
              docker volume inspect pgdata || docker volume create pgdata
              #create docker container, naming it "jrvs-psql" using "postgres" image, and mounting pgdata volume
              docker run --name jrvs-psql -e POSTGRES_PASSWORD="${db_password}" -e POSTGRES_USER="${db_username}" -d -v pgdata:/var/lib/postgresql/data -p 5432:5432 postgres
              if [ "$(echo $?)" != 0 ]; then
                echo "ERROR: failed to create new container." 1>&2
                exit 1
              fi
              exit 0
        ;;
    "start" )
              if [ "$(docker container ls -a -f name=jrvs-psql | wc -l)" = "1" ]; then
                echo "ERROR: Docker container does not exist."
                exit 1
              fi
              docker container start jrvs-psql
              exit $?
        ;;
    "stop" )
            if [ "$(docker container ls -a -f name=jrvs-psql | wc -l)" = "1" ]; then
                echo "ERROR: Docker container does not exist."
                exit 1
            fi
            docker container stop jrvs-psql
            exit $?
        ;;
    * ) echo "Invalid input. I don't know what you want. Choose either start stop create."
        exit 1
esac

exit 0