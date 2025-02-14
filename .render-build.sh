#!/bin/bash

# Automatically find Java path
export JAVA_HOME=$(update-java-alternatives -l | awk '{print $3}')
export PATH=$JAVA_HOME/bin:$PATH

# Debugging: Check Java version
echo "JAVA_HOME is set to: $JAVA_HOME"
java -version

# Make Maven wrapper executable and build
chmod +x mvnw
./mvnw clean install
