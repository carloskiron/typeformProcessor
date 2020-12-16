FROM openjdk:8

# Add Maintainer Info
LABEL maintainer="cmonvel@gmail.com"

# Add a volume pointing to /tmp
VOLUME /tmp

# Make port 8083 available to the world outside this container
EXPOSE 8083

# The application's jar file
ARG JAR_FILE=target/typeformProcessor-0.0.1.jar

# Add the application's jar to the container
ADD ${JAR_FILE} /home/user/typeformProcessor/typeformProcessor.jar

# permission to files
RUN sudo chmod -R 777 /home/user/typeformProcessor/

# Run the jar file
ENTRYPOINT ["/usr/lib/jvm/java-8-openjdk-amd64/jre/bin/java","-Djava.net.preferIPv4Stack=true","-Djava.security.egd=file:/dev/./urandom","-jar","/home/user/processManager/typeformProcessor.jar"]


