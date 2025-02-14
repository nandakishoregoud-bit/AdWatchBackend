#!/bin/bash

# Render automatically sets JAVA_HOME if JAVA_VERSION is set
echo "JAVA_HOME is set to: $JAVA_HOME"
ls -l $JAVA_HOME  # Check if Java exists
ls -l $JAVA_HOME/bin  # Check Java binaries
java -version  # Verify Java is working

# Make mvnw executable and run build
chmod +x mvnw
./mvnw clean install
