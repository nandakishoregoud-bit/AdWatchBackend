#!/bin/bash

# Detect Java path
export JAVA_HOME=$(dirname $(dirname $(which java)))

# Print JAVA_HOME to verify
echo "JAVA_HOME is set to: $JAVA_HOME"

# Make mvnw executable
chmod +x mvnw

# Run Maven build
./mvnw clean install
