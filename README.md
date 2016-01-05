# Mymicroservice

This is an example application implemented using:
- [Akka Persistence](http://doc.akka.io/docs/akka/snapshot/scala/persistence.html)
- [Dropwizard](http://www.dropwizard.io/)

If you find the example helpful, and maybe learned something new, please give it a star. 
If you have improvement suggestions please create an [issue](https://github.com/jansoren/akka-persistence-java-example/issues) or 
a pull request. Enjoy :-)

## How to start the Mymicroservice application

1. Run `mvn clean install` to build your application
1. Start application with `java -jar target/mymicroservice-server-1.0.0-SNAPSHOT.jar server config.yml`
1. To check that your application is running enter url [http://localhost:8080](http://localhost:8080)

## Health Check

To see your applications health enter url [http://localhost:8081](http://localhost:8081)

## Using GetEventstore instead of LevelDB

- Install [Docker](http://docs.docker.com/)
- Download and run the [geteventstore](https://geteventstore.com/) image `docker run -d -p 2113:2113 -p 1113:1113 adbrowne/eventstore`
- Open Docker and find the ip of your Virtual Box `docker-machine ls`
```
NAME      ACTIVE   DRIVER       STATE     URL                         SWARM   ERRORS
default   *        virtualbox   Running   tcp://192.168.99.100:2376
```
- Enter the url you found with the geteventstore port like this [http://192.168.99.100:2113/web/index.html](http://192.168.99.100:2113/web/index.html)
- Enter username `admin` and password `changeit`

### Docker cleanup

- Deletes all containers with kill `docker rm $(docker kill $(docker ps -aq))`
- Deletes all containers with stop `docker rm $(docker stop $(docker ps -aq))`
- Deletes all images `docker rmi $(docker images)`

### Docker build

- Build a image `docker build -t jansoren/eventstore .`
