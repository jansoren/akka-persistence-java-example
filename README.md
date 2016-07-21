# Mymicroservice

This is an example application implemented using:
- [Akka Persistence](http://doc.akka.io/docs/akka/snapshot/scala/persistence.html)
- [Dropwizard](http://www.dropwizard.io/)

If you find the example helpful, and maybe learned something new, please give it a star. 
If you have improvement suggestions please create an [issue](https://github.com/jansoren/akka-persistence-java-example/issues) or 
a pull request. Enjoy :-)

## How to start the Mymicroservice application

1. Clone the [restapi-codegen-maven-plugin](https://github.com/jansoren/restapi-codegen-maven-plugin) repo `git clone git@github.com:jansoren/restapi-codegen-maven-plugin.git`
1. Run `mvn clean install` in the 'restapi-codegen-maven-plugin'-folder
1. Then clone this repo `git clone git@github.com:jansoren/akka-persistence-java-example.git`
1. Run `mvn clean install` in the `akka-persistence-java-example/tree/master/mymicroservice-server`-folder
1. Start application with `java -jar target/mymicroservice-server-1.0.0-SNAPSHOT.jar server config.yml`
1. To check that your application is running enter url [http://localhost:8080](http://localhost:8080)

## Health Check

To see your applications health enter url [http://localhost:8081](http://localhost:8081)
