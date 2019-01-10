# Royal Bash

CircleCI  [![CircleCI](https://circleci.com/gh/alexanderschramm1992/royalbash.svg?style=svg)](https://circleci.com/gh/alexanderschramm1992/royalbash)

## Prerequisites
 * [Java JDK 8](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html) has to be installed on the machine
 * [Maven](https://maven.apache.org/download.cgi) has to be installed on the machine
 * [NPM](https://nodejs.org/en/) has to be installed on the machine
 * [Kotlin](https://kotlinlang.org/) is installed in your backend IDE
 * Suggested IDE is [IntelliJ](https://www.jetbrains.com/idea/download/#section=windows) for backend and [VS Code](https://code.visualstudio.com/) for frontend

## Getting started
 * Run `mvn clean install -U` to download dependencies of the backend
 * Run `npm install` to download dependencies of the frontend
 * Run `npm start` to compile and pack the frontend (keeps running)
 * Right click `/src/main/java/de.schramm.royalbash/ServerApplication` and select _Run_

## Endpoints
 * In your browser, navigate to [localhost:8080](http://localhost:3000) to find the app
 * In your browser, navigate to [localhost:8080/api](http://localhost:8080/api) to find the backend API documentation
