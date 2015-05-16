#!/bin/bash

COUNTER=1
while [  $COUNTER -lt 51 ]; do
    echo $COUNTER
    let COUNTER=COUNTER+1 
done