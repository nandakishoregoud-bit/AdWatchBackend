#!/bin/bash

# Set Java to Render's default path
export JAVA_HOME=$(dirname $(dirname $(readlink -f $(which java))))
export PATH=$JAVA_HOME/bin:$PATH

# Debugging: Print Java details
echo "JAVA_HOME is set to: $JAVA_HOME"
java -version

# Make Maven wrapper executable and build
chmod +x mvnw
./mvnw clean install
