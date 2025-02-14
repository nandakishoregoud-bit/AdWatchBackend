#!/bin/bash

# Ensure Java 17 is used
export JAVA_HOME=/opt/render/project/.render/java17
export PATH=$JAVA_HOME/bin:$PATH

# Debug: Check if Java is installed
echo "JAVA_HOME is set to: $JAVA_HOME"
ls -lah $JAVA_HOME
ls -lah $JAVA_HOME/bin
java -version

# Make Maven wrapper executable and build
chmod +x mvnw
./mvnw clean install
