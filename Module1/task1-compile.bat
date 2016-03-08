@echo off
echo "Compiling java classes"
call javac -d bin src\com\epam\javase01\t01\logic\*.java src\com\epam\javase01\t01\*.java
echo "Done"