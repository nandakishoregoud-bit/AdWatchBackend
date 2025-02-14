#!/bin/bash

# Manually set JAVA_HOME
export JAVA_HOME=/usr/lib/jvm/java-17-openjdk-amd64

# Print JAVA_HOME to verify
echo "JAVA_HOME is set to: $JAVA_HOME"

# Make mvnw executable
chmod +x mvnw

# Run Maven build
./mvnw clean install
