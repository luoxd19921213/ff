#!/bin/bash

wget --no-cookies --no-check-certificate --header "Cookie: oraclelicense=accept-securebackup-cookie" http://download.oracle.com/otn-pub/java/jdk/8u20-b26/jdk-8u20-linux-x64.rpm -O /tmp/jdk-8u20-linux-x64.rpm
rpm -i /tmp/jdk-8u20-linux-x64.rpm
rm /tmp/jdk-8u20-linux-x64.rpm
wget -O /tmp/apache-maven-3.1.1-bin.tar.gz http://ftp.jaist.ac.jp/pub/apache/maven/maven-3/3.1.1/binaries/apache-maven-3.1.1-bin.tar.gz
cd /usr/local && tar xzf /tmp/apache-maven-3.1.1-bin.tar.gz
ln -s /usr/local/apache-maven-3.1.1 /usr/local/maven
rm /tmp/apache-maven-3.1.1-bin.tar.gz
