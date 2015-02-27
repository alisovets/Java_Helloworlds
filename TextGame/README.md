## TextGame

This is helloworld project that uses maven.
This is a text game. The game uses configuration in the JSON format.
 
## How to build

You require the following to build:
* Latest stable [Oracle JDK](http://www.oracle.com/technetwork/java/)
* Latest stable [Apache Maven](http://maven.apache.org/)

Go to the project root directory and input:

mvn install

## How start

Go to the target directory and input:

java -jar textgame-0.0.1-SNAPSHOT.jar <PATH_TO_JSON_CONFIG>

For example, if you want to use the test from the project root directory then type:
 
java -jar textgame-0.0.1-SNAPSHOT.jar ../test.json      

