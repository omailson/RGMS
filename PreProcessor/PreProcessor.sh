#!/bin/bash

java -cp bin/:lib/*:../RGMS/build/classes/:../RGMS/WebContent/WEB-INF/lib/* preprocessor.PreProcessor "$1" "$2"
