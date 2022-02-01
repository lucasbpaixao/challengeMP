#!/bin/bash 
mvn compile flyway:migrate

java -jar target/challengeMP-0.0.1-SNAPSHOT.jar