# Contributing

## Environment

### Requirements
* Maven 3.3+
* Java 15
* JavaFX 15.0.1
* JUnit 4.13
* Log4j 2.14.0

### Setup
Clone the repository with ``git clone https://github.com/SirPhase/legacy.git``
Get updates with ``git pull``

### Building
From the root of the repository (within the parent directory with the `pom.xml`), run:
```
mvn clean install
```
This will create the JAR executable file in `app/target/legacy.jar`.

## Code

### Code Style

Insert line breaks when exceeding the column limit or to logically separate a thought.

Indentation follows 1 tab for `*.java` files (Indent size is 4 columns).

Variables should be descriptive and accurate. Use short variable names for iterations or loops. Java variables should
be written in camelCase.

### Rules

All pull requests to the `main` branch must have an updated `revision` version within the `pom.xml`, following
[semantic versioning](https://semver.org/). I.E. MAJOR.MINOR.PATCH

### Documentation

# Further Reading

A wiki will be set up as development continues.