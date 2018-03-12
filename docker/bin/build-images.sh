#!/usr/bin/env bash

cd $1 && \
    ./gradlew :bookit-react:npmInstall :bookit-react:build && \
    ./gradlew :bookit-ratpack:clean :bookit-ratpack:shadowJar
