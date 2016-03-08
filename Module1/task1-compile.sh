#!/bin/sh

echo "Compiling java classes"
javac -d bin src\com\epam\javase01\t01\logic\*.java src\com\epam\javase01\t01\*.java
echo "Done"