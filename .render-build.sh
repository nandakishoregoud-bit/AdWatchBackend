#!/bin/bash

# Install OpenJDK 17
echo "Installing OpenJDK 17..."
sudo apt-get update && sudo apt-get install -y openjdk-17-jdk

# Set JAVA_HOME
export JAVA_HOME=/usr/lib/jvm/java-17-openjdk-amd64
export PATH=$JAVA_HOME/bin:$PATH  # Add Java to PATH

# Verify Java installation
echo "JAVA_HOME is set to: $JAVA_HOME"
ls -l $JAVA_HOME  # Check if Java is installed
ls -l $JAVA_HOME/bin  # Check Java binaries
java -version  # Verify Java version

# Make mvnw executable and build
chmod +x mvnw
./mvnw clean install
