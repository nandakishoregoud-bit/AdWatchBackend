#!/bin/bash

# Find Java path dynamically
export JAVA_HOME=$(dirname $(dirname $(readlink -f $(which java))))
export PATH=$JAVA_HOME/bin:$PATH

# Debugging: Print Java Path
echo "JAVA_HOME is set to: $JAVA_HOME"
java -version

# Make Maven wrapper executable and build
chmod +x mvnw
./mvnw clean install
