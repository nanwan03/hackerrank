#!/bin/bash

COUNTER=1
while [  $COUNTER -lt 100 ]; do
    echo $COUNTER
    let COUNTER=COUNTER+2 
done