How code is structured?
FindRoute.java- This is main class. Implemented node creation,edges connection and uniform cost search algorithm.
Edge.java:This class contains the target nodes for connection. In other words it establishes the edges between nodes.
Node.java:Class contains the state of the node, cost and edges info.
input1.txt:A txt file which contains source,destination and pathcost.

How to run the code?
Change the directory to src folder from  ai project.
To compile all the java files in src provide following comment: javac FindRoute.java Edge.java Node.java
Note: Make sure all .class in src are deleted before compiling
To run the code:
java FindRoute input1.txt source_name destination_name
ex: 	java FindRoute input1.txt Bremen London
