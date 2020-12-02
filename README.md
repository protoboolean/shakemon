# Shakemon

A shakesperian translator for your pokemon description

## Build and run with default settings

NOTE: tested on Mac, should work seamlessly on Linux and probably on Windows

I assume you already have `git` installed and you've cloned the repo with:

    git clone https://github.com/protoboolean/shakemon

TODO: test git URL.

1. Install SDKman: an utility to download and install a variety of build tools

   Please refer to https://sdkman.io/install. Once installed the `sdk` command should be available in your command line.

2. Install JDK: Java Development Kit version 11.

       $ sdk install java 11.0.9.hs-adpt

3. Install Gradle: the project build tool of choice. NOTE: this might not be required since gradle wrapper should download it automatically.

       $ sdk install gradle 6.7.1

4. Build Shakemon: by default producing an executable jar

       $ ./gradlew build
       $ java -jar TODO/build/runnable-jar

5. [OPT TODO] Generate Docker Image

## Configuration

TODO
