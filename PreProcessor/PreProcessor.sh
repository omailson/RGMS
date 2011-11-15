#!/bin/bash

java -cp bin/:lib/* preprocessor.PreProcessor "$1" "$2"
