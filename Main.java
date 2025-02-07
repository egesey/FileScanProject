package Project3Data2112;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        ReadGraph readGraph = new ReadGraph();
        Graph graph = new Graph();
        Scanner input = new Scanner(System.in);

        readGraph.ReadGraphFromFile(graph, "/Users/egeseymen/Desktop/graph.txt");
        System.out.println("Enter two cities to check path:");
        String city1 = input.next();
        String city2 = input.next();
        
        System.out.println("=== Path Information ===");
        System.out.println("Is there a path: " + graph.IsThereAPath(city1, city2));
        graph.BFSfromTo(city1, city2);
        graph.DFSfromTo(city1, city2);
        int shortestPath = graph.WhatIsShortestPathLength(city1, city2);
        if (shortestPath != -1) {
            System.out.println("Shortest Path Length: " + shortestPath);
        }
        System.out.println("Number of simple paths: " + graph.NumberOfSimplePaths(city1, city2));
 
        System.out.println("\n=== Graph Analysis ===");
        System.out.println("Neighbors of " + city1 + ": " + graph.Neighbors(city1));
        System.out.println("Highest degree: " + graph.HighestDegree());
        System.out.println("Is the graph directed? " + graph.IsDirected());
        System.out.println("Are the cities adjacent? " + graph.AreTheyAdjacent(city1, city2));

        System.out.println("\n=== Cycle Information ===");
        System.out.println("Is there a cycle starting and ending at " + city1 + "? " + graph.IsThereACycle(city1));
        
        System.out.println("\n=== Component Information ===");
        System.out.println("Number of vertices in the component containing " + city1 + ": " + graph.NumberOfVerticesInComponent(city1));
    }
}
