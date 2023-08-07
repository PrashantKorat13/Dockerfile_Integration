FROM openjdk:17-oracle
LABEL maintainer="prashant@yahoo.co.uk"
VOLUME /tmp
COPY /target/Spring-boot-postgres-JPA-0.0.1-SNAPSHOT.jar docker-postgres-springboot-demo-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","docker-postgres-springboot-demo-0.0.1-SNAPSHOT.jar"]