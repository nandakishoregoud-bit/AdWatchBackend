#!/bin/bash

# Set JAVA_HOME manually
export JAVA_HOME=/usr/lib/jvm/java-17-openjdk-amd64
export PATH=$JAVA_HOME/bin:$PATH  # Add Java to PATH

# Print Java version to verify
echo "JAVA_HOME is set to: $JAVA_HOME"
ls -l $JAVA_HOME  # List directory to check if Java exists
ls -l $JAVA_HOME/bin  # List bin directory

# Try running Java
java -version

# Make mvnw executable and build
chmod +x mvnw
./mvnw clean install
