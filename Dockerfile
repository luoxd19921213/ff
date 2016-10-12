# Dockerfile for a Java webapp build by Maven and running on Tomcat + Apache

FROM centos:centos7

MAINTAINER luoxd (luoxd19921213@qq.com)

# Other stuff can be installed with yum
# (Note that git is quite old. If you want 1.8.x, install from source.)
ADD ./docker/etc/nginx.repo /etc/yum/repos.d/nginx.repo
RUN yum -y --noplugins --verbose update
RUN yum -y --noplugins --verbose install nginx git wget tar

# Java installation.
RUN cd /tmp &&  curl -L 'http://download.oracle.com/otn-pub/java/jdk/8u101-b13/jdk-8u101-linux-x64.tar.gz' -H 'Cookie: oraclelicense=accept-securebackup-cookie; gpw_e24=Dockerfile' | tar -xz    
RUN mkdir /tmp/aa
RUN mv /tmp/jdk1.8.0_101/ /usr/local/java/


# Tomcat 8
# Not available on yum, so install manually 
RUN wget -O /tmp/apache-tomcat-8.0.5.tar.gz http://archive.apache.org/dist/tomcat/tomcat-8/v8.0.5/bin/apache-tomcat-8.0.5.tar.gz
RUN cd /usr/local && tar xzf /tmp/apache-tomcat-8.0.5.tar.gz
RUN ln -s /usr/local/apache-tomcat-8.0.5 /usr/local/tomcat
RUN rm /tmp/apache-tomcat-8.0.5.tar.gz

# Download Maven
RUN wget -O /tmp/apache-maven-3.1.1-bin.tar.gz http://ftp.jaist.ac.jp/pub/apache/maven/maven-3/3.1.1/binaries/apache-maven-3.1.1-bin.tar.gz
RUN cd /usr/local && tar xzf /tmp/apache-maven-3.1.1-bin.tar.gz
RUN ln -s /usr/local/apache-maven-3.1.1 /usr/local/maven
RUN rm /tmp/apache-maven-3.1.1-bin.tar.gz

# Copy tomcat config file
RUN rm -f /usr/local/tomcat/conf/tomcat-users.xml
ADD ./docker/tomcat-conf /usr/local/tomcat/conf

# Copy nginx config file and delete conflicting conf
ADD ./docker/nginx-conf /etc/nginx/conf.d
RUN rm -f /etc/nginx/conf.d/default.conf

# Copy start script
ADD ./docker/start-script /usr/local
RUN chmod a+x /usr/local/start-everything.sh

# Add the application itself
RUN mkdir /webapp
Add ./SpringMaven /webapp

# Environment variables
ENV JAVA_HOME /usr/local/java/
ENV CATALINA_HOME /usr/local/tomcat
ENV MAVEN_HOME /usr/local/maven
ENV APP_HOME /webapp

# Build the app once, so we can include all the dependencies in the image
#-Dmaven.test.skip=true，不执行测试用例，也不编译测试用例类
#-DskipTests，不执行测试用例，但编译测试用例类生成相应的class文件至target/test-classes下
#rm -rf $CATALINA_HOME/webapps/* && \

RUN cd /webapp && /usr/local/maven/bin/mvn -Dmaven.test.skip=true package && \
    cp target/SpringMaven.war $CATALINA_HOME/webapps/SpringMaven.war

# Set the start script as the default command (this will be overriden if a command is passed to Docker on the commandline).
# Note that we tail Tomcat's log in order to keep the process running
# so that Docker will not shutdown the container. This is a bit of a hack.
CMD /usr/local/start-everything.sh && tail -F /usr/local/tomcat/logs/catalina.out

# Forward HTTP ports
EXPOSE 80 8080