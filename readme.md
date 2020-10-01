# Camel Visual

_A poor man's Camel route visualizer_

This project is a hacky prototype of a text based Camel route visualizer.

This tool can scan your source code for Camel routes (currently only Java)
and output a visual route graph (current text based).

This tool can be run in real time while you develop your Camel routes and
see live updates.

## How to run

The tool can be run via 

- jbang
- Maven

### Run via jbang

First you must install [jbang].

Then you can run the tool via jbang

    jbang launch@davsclaus/camel-visual

### Run via Maven

Here you first need to clone the project and then you can run via the maven exec plugin

    mvn exec:java
    
### Run Arguments

The tool accepts two arguments

1) Directory (incl sub folders) to scan for source code. Will defaults to current directory if not specified.

2) --urls  Whether to include endpoint uri information in the output 
  

[jbang]: https://jbang.dev/