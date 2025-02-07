
package Project3Data2112;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class ReadGraph {
    
    
    
    public void ReadGraphFromFile(Graph graph, String fileName) {
        try {
            Scanner scanner = new Scanner(new File(fileName));
 
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] parts = line.split("->");
                String cityName = parts[0].trim();

                graph.addCityIfAbsent(cityName);
 
                if (parts.length > 1) {
                    String[] edges = parts[1].split(",");
                    for (String edge : edges) {
                        String[] edgeParts = edge.split(":");
                        String toCity = edgeParts[0].trim();
                        int weight = Integer.parseInt(edgeParts[1].trim());

                        graph.addCityIfAbsent(toCity);
                        graph.addEdge(cityName, toCity, weight);
                    } 
                }  
            }
            System.out.println("Graph successfully loaded.");
        } catch (FileNotFoundException e) {
            System.out.println("Error: File not found.");
        }
    }

 
}  
