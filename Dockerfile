FROM  openjdk:11

COPY target/acodecompany-0.0.1-SNAPSHOT.jar acodecompany-0.0.1-SNAPSHOT.jar
EXPOSE 8080
CMD ["java","-jar","acodecompany-0.0.1-SNAPSHOT.jar"]