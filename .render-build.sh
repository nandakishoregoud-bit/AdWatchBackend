#!/bin/bash
export JAVA_HOME=/opt/render/project/.render/java17
chmod +x mvnw
./mvnw clean install
