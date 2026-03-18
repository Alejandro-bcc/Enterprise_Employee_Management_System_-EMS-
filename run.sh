#!/usr/bin/env bash

javac -cp "lib/gson-2.10.1.jar" -d out src/**/*.java
java -cp "out:lib/gson-2.10.1.jar:." src.view.LoginWindow

