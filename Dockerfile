ARG BASE_CONTAINER=maritama/DSC180-Capstone-Web

FROM $BASE_CONTAINER

LABEL maintainer = "Yicen Ma<yim095@ucsd.edu>"

USER root
RUN apt-get update -y
RUN apt-get install -y htop gcc openjdk-8-jdk ant
RUN cd /usr/local
RUN apt-get install wget
RUN wget https://apache.website-solution.net/tomcat/tomcat-9/v9.0.43.tar.gz
RUN tar zxf apache-tomcat-9.0.43.tar.gz
ENV CATALINA_HOME /usr/local/apache-tomcat-9.0.43
ENV PATH $PATH:$CATALINA_HOME/bin
COPY python.war /usr/local/apache-tomcat-9.0.43/bin/startup.sh && tail -F /usr/local/apache-tomcat-9.0.43/logs/catalina.out

RUN pip install --no-cache-dir requests

COPY /run_jupyter.sh /
RUN chmod 755 /run_jupyter.sh
USER $NB_UID
