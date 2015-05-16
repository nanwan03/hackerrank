#!/bin/bash

while read line
do
    my_array=("${my_array[@]}" $line)
done
 my_array=("${my_array[@]}" "${my_array[@]}" "${my_array[@]}")
echo ${my_array[@]}