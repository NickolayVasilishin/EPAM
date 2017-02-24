#!/bin/sh

pushd bin
echo "Running Main"
java -cp . com.epam.javase01.t01.Main
echo "Done"
popd