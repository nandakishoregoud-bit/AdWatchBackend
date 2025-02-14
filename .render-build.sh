#!/bin/bash

# Install Java 17
apt-get update && apt-get install -y openjdk-17-jdk

# Set Java environment
export JAVA_HOME=/usr/lib/jvm/java-17-openjdk-amd64
export PATH=$JAVA_HOME/bin:$PATH

# Debugging: Check Java version
echo "JAVA_HOME is set to: $JAVA_HOME"
java -version

# Make Maven wrapper executable and build
chmod +x mvnw
./mvnw clean install
