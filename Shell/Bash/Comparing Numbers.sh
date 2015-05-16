#!/bin/bash

read a
read b
if [ $a -eq $b ]
then
  echo "X is equal to Y"
fi
if [ $a -lt $b ]
then
  echo "X is less than Y"
fi
if [ $a -gt $b ]
then
  echo "X is greater than Y"
fi