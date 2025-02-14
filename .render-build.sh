#!/bin/bash
export JAVA_HOME=/opt/render/project/.render/java17
export PATH=$JAVA_HOME/bin:$PATH

echo "JAVA_HOME is set to: $JAVA_HOME"
java -version

chmod +x mvnw
./mvnw clean install
