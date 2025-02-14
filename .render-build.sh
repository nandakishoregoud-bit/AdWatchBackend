#!/bin/bash

# Print environment variables for debugging
echo "JAVA_HOME is set to: $JAVA_HOME"

# Check if Java is installed
which java
java -version

# List contents of JAVA_HOME (if it's set)
if [ -n "$JAVA_HOME" ]; then
  ls -l "$JAVA_HOME"
  ls -l "$JAVA_HOME/bin"
fi

# Run Maven Build
chmod +x mvnw
./mvnw clean install
