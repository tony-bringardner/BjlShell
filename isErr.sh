#!/bin/bash
rm -i target/Co01.txt
if [ $? -eq 0 ]; then
    echo "Command Executed Successfully"
else
    echo "Command Failed"
fi
