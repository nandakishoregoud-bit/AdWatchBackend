#!/bin/bash

# Print JAVA_HOME path
echo "JAVA_HOME is set to: $JAVA_HOME"

# Verify if Java is installed
ls -l $JAVA_HOME
ls -l $JAVA_HOME/bin
java -version  # Check Java version

# Make mvnw executable and run Maven build
chmod +x mvnw
./mvnw clean install
