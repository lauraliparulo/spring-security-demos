# SPRING-tests-demo

this code is based on the example found on the book "Learning Spring Boot 3.0" by Packt Publishing


To run the application, first make sure node, npm and the packages listed in the package.json files are installed by runing:

> mvn generate-resources

The install the application:

> mvn install

Then run the Application java class called "RestReactDemoApplication".

If you have the docker demon running on Ubuntu and can't run the containerized tests and you are on Ubuntu:

> sudo groupadd docker

> sudo gpasswd -a $USER docker

> sudo service docker restart

> sudo chmod 666 /var/run/docker.soc